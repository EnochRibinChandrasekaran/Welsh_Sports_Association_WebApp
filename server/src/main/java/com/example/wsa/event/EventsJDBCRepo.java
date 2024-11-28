package com.example.wsa.event;

import com.example.wsa.event.exception.EventRepositoryException;
import com.example.wsa.event.exception.EventServiceException;
import com.example.wsa.organiser.Organiser;
import com.example.wsa.organiser.OrganiserRespository;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of the {@link EventRepo} interface using JDBC and JPA repositories.
 * Provides methods for accessing and manipulating event data from the database.
 */
@Slf4j
@Repository
public class EventsJDBCRepo implements EventRepo {

  private final JdbcTemplate jdbc;

  private final RowMapper<EventDTO> eventRowMapper = (rs, rowNum) -> new EventDTO(
          rs.getInt("id"),
          rs.getString("title"),
          rs.getString("description"),
          rs.getString("address"),
          rs.getString("landmark"),
          rs.getDate("date").toLocalDate(),
          rs.getTimestamp("start_time").toLocalDateTime(),
          rs.getTimestamp("end_time").toLocalDateTime()
  );

  private final EventRepository eventRepository;
  private final OrganiserRespository organiserRepository;

  /**
   * Constructs a new EventsJDBCRepo with the specified JdbcTemplate,
   * EventRepository, and OrganiserRepository.
   *
   * @param jdbc                the JdbcTemplate used for database access
   * @param eventRepository     the JPA repository for Event entities
   * @param organiserRepository the JPA repository for Organiser entities
   */
  public EventsJDBCRepo(JdbcTemplate jdbc,
                        EventRepository eventRepository,
                        OrganiserRespository organiserRepository) {
    this.jdbc = jdbc;
    this.eventRepository = eventRepository;
    this.organiserRepository = organiserRepository;
  }

  /**
   * Retrieves all events with pagination support.
   *
   * @param pageable the pagination information
   * @return a page of EventDTO objects
   */
  @Override
  public Page<EventDTO> getAllEvents(Pageable pageable) {
    log.debug("Entering getAllEvents with pageable: {}", pageable);

    String sql = "SELECT * FROM event ORDER BY date DESC LIMIT ? OFFSET ?";
    String countSql = "SELECT COUNT(*) FROM event";

    // Count total records
    int total = jdbc.queryForObject(countSql, Integer.class);
    log.debug("Total number of events: {}", total);

    // Query paginated results
    List<EventDTO> events = jdbc.query(sql,
            eventRowMapper,
            pageable.getPageSize(),
            pageable.getOffset());

    log.debug("Retrieved {} events for the current page", events.size());

    return new PageImpl<>(events, pageable, total);
  }

  /**
   * Retrieves an event by its ID.
   *
   * @param id the ID of the event
   * @return the EventDTO representing the event
   * @throws EventServiceException if the event is not found or an error occurs
   */
  @Override
  @Transactional(readOnly = true)
  public EventDTO getEvent(int id) throws EventServiceException {
    log.debug("Entering getEvent with id: {}", id);
    try {
      List<Object[]> results = eventRepository.getEventDetailsByEventID(id);
      if (results != null && !results.isEmpty()) {
        Object[] result = results.get(0);
        EventDTO eventDTO = mapToEventDTO(result);
        log.debug("Retrieved event: {}", eventDTO);
        return eventDTO;
      } else {
        log.error("Event not found with id: {}", id);
        throw new EventRepositoryException("Event not found with id: " + id);
      }
    } catch (EventRepositoryException e) {
      log.error("Error fetching event with id {}: {}", id, e.getMessage());
      throw new EventServiceException("Error fetching event with id: " + id, e);
    } catch (Exception e) {
      log.error("Unexpected error occurred while fetching event with id {}: {}",
              id, e.getMessage());
      throw new EventServiceException(
              "An unexpected error occurred while fetching event details", e);
    }
  }

  /**
   * Updates the information of an existing event.
   *
   * @param id    the ID of the event to update
   * @param event the EventDTO containing the updated information
   * @return the updated EventDTO
   * @throws EventServiceException if the event is not found or an error occurs during update
   */
  @Override
  public EventDTO updateEventInfo(int id, EventDTO event) throws EventServiceException {
    log.debug("Entering updateEventInfo with id: {} and event: {}", id, event);
    try {
      Event eventDetails = eventRepository.findById(id)
              .orElseThrow(() -> new EventRepositoryException("Event not found with id: " + id));

      // Update fields
      eventDetails.setTitle(event.getName());
      eventDetails.setDescription(event.getDescription());
      eventDetails.setDbsRequired(event.getDbsRequired());
      eventDetails.setAddress(event.getAddress());
      eventDetails.setCity(event.getCity());
      eventDetails.setPostalCode(event.getPostalCode());
      eventDetails.setLandmark(event.getLandmark());
      eventDetails.setRolesNeeded(String.join(",", event.getRolesNeeded()));
      eventDetails.setRewardsOffering(event.getRewardsOffering());
      eventDetails.setImage(event.getEventImage());
      eventDetails.setDate(event.getDate());
      eventDetails.setStartTime(event.getStartTime());
      eventDetails.setEndTime(event.getEndTime());
      eventDetails.setAccessibilityAssistanceProvided(
              String.join(",", event.getAccessibilityAssistance()));

      log.debug("Updating event: {}", eventDetails);

      Event savedEvent = eventRepository.save(eventDetails);
      EventDTO updatedEventDTO = convertToDTO(savedEvent, event);
      log.debug("Updated event saved: {}", updatedEventDTO);

      return updatedEventDTO;
    } catch (EventRepositoryException e) {
      log.error("Event not found with id {}: {}", id, e.getMessage());
      throw new EventServiceException("Event not found with id: " + id, e);
    } catch (Exception e) {
      log.error("An unexpected error occurred while updating event with id {}: {}",
              id, e.getMessage());
      throw new EventServiceException(
              "An unexpected error occurred while updating event details", e);
    }
  }

  /**
   * Saves a new event to the database.
   *
   * @param event the EventDTO containing the event information
   * @return the saved EventDTO
   * @throws EventServiceException if an error occurs during saving
   */
  @Override
  public EventDTO saveEvent(EventDTO event) throws EventServiceException {
    log.debug("Entering saveEvent with event: {}", event);
    try {
      Event eventDetails = convertToEntity(event);
      Organiser organiser = organiserRepository.findById(event.getOrganiserID())
              .orElseThrow(() -> new EventRepositoryException(
                      "Organiser not found with id: " + event.getOrganiserID()));

      eventDetails.setOrganiser(organiser);

      Event savedEvent = eventRepository.save(eventDetails);
      EventDTO savedEventDTO = convertToDTO(savedEvent, event);

      log.debug("Event saved successfully: {}", savedEventDTO);

      return savedEventDTO;
    } catch (EventRepositoryException e) {
      log.error("Error fetching organiser with id {}: {}", event.getOrganiserID(), e.getMessage());
      throw new EventServiceException("Error fetching organiser data", e);
    } catch (Exception e) {
      log.error("An unexpected error occurred while saving event: {}", e.getMessage());
      throw new EventServiceException("An unexpected error occurred while saving event", e);
    }
  }

  /**
   * Retrieves event details by event ID.
   *
   * @param id the ID of the event
   * @return the EventDTO containing the event details
   * @throws EventServiceException if the event is not found or an error occurs
   */
  @Override
  public EventDTO getEventDetails(int id) throws EventServiceException {
    log.debug("Entering getEventDetails with id: {}", id);
    try {
      Event eventDetails = eventRepository.findById(id)
              .orElseThrow(() -> new EventRepositoryException("Event not found with id: " + id));

      EventDTO eventDTO = eventDTOMapper(eventDetails);

      log.debug("Retrieved event details: {}", eventDTO);

      return eventDTO;
    } catch (EventRepositoryException e) {
      log.error("Event not found with id {}: {}", id, e.getMessage());
      throw new EventServiceException("Event not found with id: " + id, e);
    } catch (Exception e) {
      log.error("An unexpected error occurred while fetching event details for id {}: {}",
              id, e.getMessage());
      throw new EventServiceException(
              "An unexpected error occurred while fetching event details", e);
    }
  }

  /**
   * Maps an Event entity to an EventDTO.
   *
   * @param eventDetails the Event entity
   * @return the EventDTO
   */
  private EventDTO eventDTOMapper(Event eventDetails) {
    log.debug("Mapping Event to EventDTO: {}", eventDetails);
    EventDTO eventDTO = new EventDTO();
    eventDTO.setId(eventDetails.getId());
    eventDTO.setName(eventDetails.getTitle());
    eventDTO.setDescription(eventDetails.getDescription());
    eventDTO.setDbsRequired(eventDetails.getDbsRequired());
    eventDTO.setAddress(eventDetails.getAddress());
    eventDTO.setCity(eventDetails.getCity());
    eventDTO.setPostalCode(eventDetails.getPostalCode());
    eventDTO.setLandmark(eventDetails.getLandmark());
    eventDTO.setEventImage(eventDetails.getImage());
    eventDTO.setDate(eventDetails.getDate());
    eventDTO.setStartTime(eventDetails.getStartTime());
    eventDTO.setEndTime(eventDetails.getEndTime());
    eventDTO.setAccessibilityAssistance(Arrays
            .asList(eventDetails.getAccessibilityAssistanceProvided().split(",")));
    eventDTO.setRewardsOffering(eventDetails.getRewardsOffering());
    eventDTO.setRolesNeeded(Arrays.asList(eventDetails.getRolesNeeded().split(",")));
    log.debug("Mapped EventDTO: {}", eventDTO);
    return eventDTO;
  }

  /**
   * Converts an EventDTO to an Event entity.
   *
   * @param event the EventDTO
   * @return the Event entity
   */
  private Event convertToEntity(EventDTO event) {
    log.debug("Converting EventDTO to Event entity: {}", event);
    Event eventDetails = new Event();

    eventDetails.setTitle(event.getName());
    eventDetails.setDescription(event.getDescription());
    eventDetails.setDbsRequired(event.getDbsRequired());
    eventDetails.setAddress(event.getAddress());
    eventDetails.setCity(event.getCity());
    eventDetails.setPostalCode(event.getPostalCode());
    eventDetails.setLandmark(event.getLandmark());
    eventDetails.setRolesNeeded(String.join(",", event.getRolesNeeded()));
    eventDetails.setRewardsOffering(event.getRewardsOffering());

    String base64Image = event.getBase64Image();
    if (base64Image != null && !base64Image.isEmpty()) {
      if (base64Image.contains(",")) {
        base64Image = base64Image.split(",")[1];
      }
      byte[] imageBytes = Base64.getDecoder().decode(base64Image);
      eventDetails.setImage(imageBytes);
    }

    eventDetails.setDate(event.getDate());
    eventDetails.setStartTime(event.getStartTime());
    eventDetails.setEndTime(event.getEndTime());
    eventDetails.setAccessibilityAssistanceProvided(
            String.join(",", event.getAccessibilityAssistance()));
    eventDetails.setApproved(false);
    eventDetails.setRewardsOffering(event.getRewardsOffering());

    log.debug("Converted Event entity: {}", eventDetails);
    return eventDetails;
  }

  /**
   * Converts an Event entity to an EventDTO, using information from the original EventDTO.
   *
   * @param event    the Event entity
   * @param eventOrg the original EventDTO containing organiser information
   * @return the EventDTO
   */
  private EventDTO convertToDTO(Event event, EventDTO eventOrg) {
    log.debug("Converting Event entity to EventDTO: {}", event);
    EventDTO eventDTO = new EventDTO();
    eventDTO.setId(event.getId());
    eventDTO.setName(event.getTitle());
    eventDTO.setDescription(event.getDescription());
    eventDTO.setDbsRequired(event.getDbsRequired());
    eventDTO.setAddress(event.getAddress());
    eventDTO.setCity(event.getCity());
    eventDTO.setPostalCode(event.getPostalCode());
    eventDTO.setLandmark(event.getLandmark());
    eventDTO.setRolesNeeded(Arrays.asList(event.getRolesNeeded().split(",")));
    eventDTO.setRewardsOffering(event.getRewardsOffering());
    eventDTO.setEventImage(event.getImage());
    eventDTO.setDate(event.getDate());
    eventDTO.setStartTime(event.getStartTime());
    eventDTO.setEndTime(event.getEndTime());
    eventDTO.setOrganiserID(eventOrg.getOrganiserID());
    eventDTO.setOrganiserName(eventOrg.getOrganiserName());

    log.debug("Converted EventDTO: {}", eventDTO);
    return eventDTO;
  }

  /**
   * Maps a result set to an EventDTO.
   *
   * @param result the result set object array
   * @return the EventDTO
   */
  private EventDTO mapToEventDTO(Object[] result) {
    log.debug("Mapping result set to EventDTO: {}", Arrays.toString(result));
    EventDTO eventDTO = new EventDTO();
    eventDTO.setOrganiserID((Integer) result[0]);
    eventDTO.setOrganiserName((String) result[1]);
    eventDTO.setId((Integer) result[2]);
    eventDTO.setName((String) result[3]);
    eventDTO.setDescription((String) result[4]);
    eventDTO.setDbsRequired((Boolean) result[5]);
    eventDTO.setAddress((String) result[6]);
    eventDTO.setCity((String) result[7]);
    eventDTO.setPostalCode((String) result[8]);
    eventDTO.setLandmark((String) result[9]);
    eventDTO.setRolesNeeded(Arrays.asList(((String) result[10]).split(",")));
    eventDTO.setRewardsOffering((String) result[11]);
    eventDTO.setEventImage((byte[]) result[12]);
    eventDTO.setDate(((Date) result[13]).toLocalDate());
    eventDTO.setStartTime(((Timestamp) result[14]).toLocalDateTime());
    eventDTO.setEndTime(((Timestamp) result[15]).toLocalDateTime());
    eventDTO.setAccessibilityAssistance(Arrays.asList(((String) result[16]).split(",")));
    log.debug("Mapped EventDTO: {}", eventDTO);
    return eventDTO;
  }
}
