package com.example.wsa.organiser;

import com.example.wsa.event.EventDTO;
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * Repository class that implements the OrganiserDashboardRepo interface to manage
 * database operations related to an organiser's dashboard.
 * This class uses JdbcTemplate for querying the database and maps the results to EventDTO objects.
 */
@Repository
@Slf4j
public class OrganiserDashboardJDBC implements OrganiserDashboardRepo {

  /**
   * JdbcTemplate instance used for executing SQL queries.
   */
  private final JdbcTemplate jdbc;

  /**
   * RowMapper instance for mapping SQL result set rows to EventDTO objects.
   * Maps the fields of the 'event' table to the corresponding fields in EventDTO.
   */
  private final RowMapper<EventDTO> eventRowMapper = (rs, rowNum) -> {
    EventDTO eventDTO = new EventDTO();
    eventDTO.setId(rs.getInt("id"));
    eventDTO.setName(rs.getString("title"));
    eventDTO.setDescription(rs.getString("description"));
    eventDTO.setDbsRequired(rs.getBoolean("dbs_required"));
    eventDTO.setAddress(rs.getString("address"));
    eventDTO.setCity(rs.getString("city"));
    eventDTO.setPostalCode(rs.getString("postal_code"));
    eventDTO.setLandmark(rs.getString("landmark"));
    eventDTO.setRolesNeeded(Arrays.asList(rs.getString("roles_needed").split(",")));
    eventDTO.setRewardsOffering(rs.getString("rewards_offering"));
    eventDTO.setEventImage(rs.getBytes("image"));
    eventDTO.setDate(rs.getDate("date").toLocalDate());
    eventDTO.setStartTime(rs.getTimestamp("start_time") != null
            ? rs.getTimestamp("start_time").toLocalDateTime() : null);
    eventDTO.setEndTime(rs.getTimestamp("end_time") != null
            ? rs.getTimestamp("end_time").toLocalDateTime() : null);
    eventDTO.setAccessibilityAssistance(
            Arrays.asList(rs.getString("accessibility_assistance_provided").split(",")));
    eventDTO.setApproved(rs.getBoolean("approved"));
    return eventDTO;
  };

  /**
   * Constructs a new OrganiserDashboardJDBC with the given JdbcTemplate.
   *
   * @param jdbc the JdbcTemplate used for executing SQL queries
   */
  public OrganiserDashboardJDBC(JdbcTemplate jdbc) {
    this.jdbc = jdbc;
  }

  /**
   * Retrieves a list of upcoming events for a specific organiser.
   *
   * @param organiserId the ID of the organiser
   * @return a list of EventDTO objects representing upcoming events
   */
  @Override
  public List<EventDTO> getUpcomingEvents(int organiserId) {
    log.debug("Entering getUpcomingEvents() with organiserId: {}", organiserId);
    String sql = "SELECT * FROM event "
            + "WHERE date >= CURDATE() AND organiser_id = ? "
            + "ORDER BY date ASC";
    List<EventDTO> events = jdbc.query(sql, eventRowMapper, organiserId);
    log.debug("Retrieved {} upcoming events for organiserId: {}", events.size(), organiserId);
    return events;
  }

  /**
   * Retrieves a list of past events for a specific organiser.
   *
   * @param organiserId the ID of the organiser
   * @return a list of EventDTO objects representing past events
   */
  @Override
  public List<EventDTO> getPastEvents(int organiserId) {
    log.debug("Entering getPastEvents() with organiserId: {}", organiserId);
    String sql = "SELECT * FROM event "
            + "WHERE date < CURDATE() "
            + "AND organiser_id = ? "
            + "ORDER BY date DESC";
    List<EventDTO> events = jdbc.query(sql, eventRowMapper, organiserId);
    log.debug("Retrieved {} past events for organiserId: {}", events.size(), organiserId);
    return events;
  }
}
