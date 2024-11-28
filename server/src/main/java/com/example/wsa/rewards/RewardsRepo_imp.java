package com.example.wsa.rewards;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * Implementation of the RewardsRepo interface for accessing the database.
 */
@Repository
@Slf4j
public class RewardsRepo_imp implements RewardsRepo {

  private final JdbcTemplate jdbc;

  private final RowMapper<RewardThreshold> rewardThresholdMapper = (rs, rowNum) ->
          new RewardThreshold(
                  rs.getLong("id"),
                  rs.getString("imageName"),
                  rs.getInt("pointsRequired")
          );

  /**
   * Constructs a new RewardsRepo_imp with the specified JdbcTemplate.
   *
   * @param jdbc the JdbcTemplate used for database access
   */
  public RewardsRepo_imp(JdbcTemplate jdbc) {
    this.jdbc = jdbc;
  }

  @Override
  public void add_points(long volunteer_id, long event_id, long amount) {
    try {
      String sql = """
              INSERT INTO point_allocations (volunteer_id, event_id, amount)
              VALUES (?, ?, ?)
              """;
      jdbc.update(sql, volunteer_id, event_id, amount);
      log.debug("Added points: {} for volunteer_id: {}, event_id: {}",
              amount, volunteer_id, event_id);
    } catch (DataAccessException e) {
      log.error("add_points Error: {}", e.getMessage(), e);
      throw new RuntimeException(e);
    }
  }

  @Override
  public List<Integer> return_registered_events_list(long volunteer_id) {
    try {
      String sql = "SELECT event_id FROM volunteer_event WHERE volunteer_id = ?";
      List<Integer> eventIds = jdbc.queryForList(sql, Integer.class, volunteer_id);
      log.debug("Retrieved registered events for volunteer_id {}: {}", volunteer_id, eventIds);
      return eventIds;
    } catch (DataAccessException e) {
      log.error("return_registered_events_list Error: {}", e.getMessage(), e);
      throw new RuntimeException(e);
    }
  }

  @Override
  public Boolean isAttemptedAllocationPresent(long volunteer_id, long event_id) {
    try {
      String sql = """
              SELECT EXISTS (
                  SELECT 1 FROM point_allocations
                  WHERE volunteer_id=? AND event_id=?
              ) AS entry_exists
              """;
      Boolean exists = jdbc.queryForObject(sql, Boolean.class, volunteer_id, event_id);
      log.debug("isAttemptedAllocationPresent for volunteer_id: {}, event_id: {}: {}",
              volunteer_id, event_id, exists);
      return exists;
    } catch (DataAccessException e) {
      log.error("isAttemptedAllocationPresent Error: {}", e.getMessage(), e);
      throw new RuntimeException(e);
    }
  }

  @Override
  public String getEventExpiryDate(long event_id) {
    try {
      String sql = "SELECT date FROM event WHERE id = ?";
      String date = jdbc.queryForObject(sql, new Object[]{event_id}, String.class);
      log.debug("getEventExpiryDate for event_id {}: {}", event_id, date);
      return date;
    } catch (DataAccessException e) {
      log.error("getEventExpiryDate Error: {}", e.getMessage(), e);
      throw new RuntimeException(e);
    }
  }

  @Override
  public String getEventExpiryTime(long event_id) {
    try {
      String sql = "SELECT end_time FROM event WHERE id = ?";
      String time = jdbc.queryForObject(sql, String.class, event_id);
      log.debug("getEventExpiryTime for event_id {}: {}", event_id, time);
      return time;
    } catch (DataAccessException e) {
      log.error("getEventExpiryTime Error: {}", e.getMessage(), e);
      throw new RuntimeException(e);
    }
  }

  @Override
  public Integer retrieve_point_total(long volunteer_id) {
    try {
      String sql = "SELECT SUM(amount) FROM point_allocations WHERE volunteer_id = ?";
      Integer totalPoints = jdbc.queryForObject(sql, Integer.class, volunteer_id);
      log.debug("retrieve_point_total for volunteer_id {}: {}", volunteer_id, totalPoints);
      return totalPoints;
    } catch (DataAccessException e) {
      log.error("retrieve_point_total Error: {}", e.getMessage(), e);
      throw new RuntimeException(e);
    }
  }

  @Override
  public List<RewardThreshold> retrieve_all_reward_items() {
    try {
      String sql = "SELECT * FROM reward_point_requirements";
      List<RewardThreshold> rewardThresholds = jdbc.query(sql, rewardThresholdMapper);
      log.debug("Retrieved all reward thresholds: {}", rewardThresholds);
      return rewardThresholds;
    } catch (DataAccessException e) {
      log.error("retrieve_all_reward_items Error: {}", e.getMessage(), e);
      throw new RuntimeException(e);
    }
  }
}
