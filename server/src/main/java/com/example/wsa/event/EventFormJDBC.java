package com.example.wsa.event;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Repository class for handling event form database operations.
 * Uses JdbcTemplate to interact with the database for CRUD operations related to events.
 */
@Repository
@Slf4j
public class EventFormJDBC {

  /**
   * The JdbcTemplate instance used for database operations.
   */
  @Autowired
  private JdbcTemplate jdbcTemplate;

  /**
   * Saves event data into the database.
   *
   * @param eventDTO the EventDTO object containing event data to be saved
   */
  public void saveEvent(EventDTO eventDTO) {
    String sql = "INSERT INTO event "
            + "(title, description, address, city, postal_code, landmark, date, "
            + "start_time, end_time, dbs_required, accessibility_assistance_provided, "
            + "roles_needed, image, rewards_offering) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    try {
      jdbcTemplate.update(sql,
              eventDTO.getName(),
              eventDTO.getDescription(),
              eventDTO.getAddress(),
              eventDTO.getCity(),
              eventDTO.getPostalCode(),
              eventDTO.getLandmark(),
              eventDTO.getDate(),
              eventDTO.getStartTime(),
              eventDTO.getEndTime(),
              eventDTO.getDbsRequired(),
              String.join(",", eventDTO.getAccessibilityAssistance()),
              String.join(",", eventDTO.getRolesNeeded()),
              eventDTO.getEventImage(),
              eventDTO.getRewardsOffering()
      );
      log.debug("Event saved: {}", eventDTO.getName());
    } catch (DataAccessException e) {
      log.error("Error saving event: {}", eventDTO.getName(), e);
      throw e;
    }
  }

  /**
   * Updates existing event data in the database.
   *
   * @param id       the ID of the event to be updated
   * @param eventDTO the EventDTO object containing the updated event data
   */
  public void updateEvent(int id, EventDTO eventDTO) {
    String sql = "UPDATE event SET title = ?, description = ?, address = ?, "
            + "city = ?, postal_code = ?, landmark = ?, date = ?, start_time = ?, "
            + "end_time = ?, dbs_required = ?, accessibility_assistance_provided = ?, "
            + "roles_needed = ?, rewards_offering = ?, approved = ?";
    if (eventDTO.getEventImage() != null) {
      sql += ", image = ?";
    }
    sql += " WHERE id = ?";

    try {
      if (eventDTO.getEventImage() != null) {
        jdbcTemplate.update(sql,
                eventDTO.getName(),
                eventDTO.getDescription(),
                eventDTO.getAddress(),
                eventDTO.getCity(),
                eventDTO.getPostalCode(),
                eventDTO.getLandmark(),
                eventDTO.getDate(),
                eventDTO.getStartTime(),
                eventDTO.getEndTime(),
                eventDTO.getDbsRequired(),
                String.join(",", eventDTO.getAccessibilityAssistance()),
                String.join(",", eventDTO.getRolesNeeded()),
                eventDTO.getRewardsOffering(),
                eventDTO.getApproved(),
                eventDTO.getEventImage(),
                id
        );
      } else {
        jdbcTemplate.update(sql,
                eventDTO.getName(),
                eventDTO.getDescription(),
                eventDTO.getAddress(),
                eventDTO.getCity(),
                eventDTO.getPostalCode(),
                eventDTO.getLandmark(),
                eventDTO.getDate(),
                eventDTO.getStartTime(),
                eventDTO.getEndTime(),
                eventDTO.getDbsRequired(),
                String.join(",", eventDTO.getAccessibilityAssistance()),
                String.join(",", eventDTO.getRolesNeeded()),
                eventDTO.getRewardsOffering(),
                eventDTO.getApproved(),
                id
        );
      }
      log.debug("Event updated: {}", eventDTO.getName());
    } catch (DataAccessException e) {
      log.error("Error updating event with id: {}", id, e);
      throw e;
    }
  }

  /**
   * Fetches event data by ID from the database.
   *
   * @param id the ID of the event to retrieve
   * @return an Optional containing the EventDTO if found, or an empty Optional if not found
   */
  public Optional<EventDTO> getEventById(int id) {
    String sql = "SELECT * FROM event WHERE id = ?";
    try {
      EventDTO event = jdbcTemplate.queryForObject(
              sql, new Object[]{id}, this::mapEventFromResultSet);
      log.debug("Event retrieved with id: {}", id);
      return Optional.ofNullable(event);  // Safeguard against null
    } catch (EmptyResultDataAccessException e) {
      log.warn("No event found with id: {}", id);
      return Optional.empty();
    } catch (DataAccessException e) {
      log.error("Error accessing database for event with id: {}", id, e);
      return Optional.empty();
    }
  }

  /**
   * Maps a ResultSet row to an EventDTO object.
   *
   * @param rs     the ResultSet
   * @param rowNum the row number
   * @return an EventDTO object
   * @throws SQLException if an SQL error occurs
   */
  private EventDTO mapEventFromResultSet(ResultSet rs, int rowNum) throws SQLException {
    EventDTO eventDTO = new EventDTO();
    eventDTO.setId(rs.getInt("id"));
    eventDTO.setName(rs.getString("title"));
    eventDTO.setDescription(rs.getString("description"));
    eventDTO.setAddress(rs.getString("address"));
    eventDTO.setCity(rs.getString("city"));
    eventDTO.setPostalCode(rs.getString("postal_code"));
    eventDTO.setLandmark(rs.getString("landmark"));
    eventDTO.setDate(rs.getDate("date").toLocalDate());
    eventDTO.setStartTime(rs.getTimestamp("start_time").toLocalDateTime());
    eventDTO.setEndTime(rs.getTimestamp("end_time").toLocalDateTime());
    eventDTO.setDbsRequired(rs.getBoolean("dbs_required"));

    // Handle comma-separated values
    eventDTO.setAccessibilityAssistance(
            Arrays.asList(rs.getString("accessibility_assistance_provided").split(",")));
    eventDTO.setRolesNeeded(Arrays.asList(rs.getString("roles_needed").split(",")));
    eventDTO.setRewardsOffering(rs.getString("rewards_offering"));

    // Handle image bytes
    byte[] imageBytes = rs.getBytes("image");
    eventDTO.setEventImage(imageBytes);

    return eventDTO;
  }
}
