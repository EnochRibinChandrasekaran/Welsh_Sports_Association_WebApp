package com.example.wsa.event;

import com.example.wsa.event.exception.EventControllerException;
import com.example.wsa.event.exception.EventException;
import com.example.wsa.event.exception.EventServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing events.
 */
@Slf4j
@RestController
@RequestMapping("/api")
public class EventController {

  private final EventRepo eventRepo;

  /**
   * Constructs a new EventController with the specified EventRepo.
   *
   * @param eventRepo the repository used for event operations
   */
  @Autowired
  public EventController(EventRepo eventRepo) {
    this.eventRepo = eventRepo;
  }

  /**
   * Retrieves a paginated list of events.
   *
   * @param page the page number to retrieve, default is 0
   * @param size the size of the page, default is 10
   * @return a Page of EventDTO objects
   */
  @GetMapping("/events")
  public Page<EventDTO> getEvents(
          @RequestParam(defaultValue = "0") int page,
          @RequestParam(defaultValue = "10") int size) {
    log.debug("Entering getEvents() with page: {}, size: {}", page, size);
    Pageable pageable = PageRequest.of(page, size);
    Page<EventDTO> events = eventRepo.getAllEvents(pageable);
    log.debug("Retrieved events: {}", events.getContent());
    return events;
  }

  /**
   * Retrieves a specific event by its ID.
   *
   * @param id the ID of the event
   * @return a ResponseEntity containing the EventDTO
   * @throws EventException if the event cannot be retrieved
   */
  @GetMapping("/event/{id}")
  public ResponseEntity<EventDTO> getEvent(@PathVariable int id) throws EventException {
    try {
      log.debug("Entering getEvent() with id: {}", id);
      EventDTO eventDetails = eventRepo.getEvent(id);
      log.debug("Retrieved event: {}", eventDetails);
      return ResponseEntity.ok(eventDetails);
    } catch (EventServiceException e) {
      log.error("Error in getEvent() for id: {}", id, e);
      throw new EventControllerException("Event Service Exception getEvent()", e);
    }
  }

  /**
   * Updates an existing event with the given ID.
   *
   * @param id    the ID of the event to update
   * @param event the EventDTO containing updated event data
   * @return a ResponseEntity containing the updated EventDTO
   * @throws EventException if the event cannot be updated
   */
  @PutMapping("/event/{id}")
  public ResponseEntity<EventDTO> updateEventInfo(@PathVariable int id,
                                                  @RequestBody EventDTO event)
          throws EventException {
    try {
      log.debug("Entering updateEventInfo() with id: {}, event: {}", id, event);
      EventDTO updatedEvent = eventRepo.updateEventInfo(id, event);
      log.debug("Updated event: {}", updatedEvent);
      return ResponseEntity.ok(updatedEvent);
    } catch (EventServiceException e) {
      log.error("Error in updateEventInfo() for id: {}", id, e);
      throw new EventControllerException("Event Service Exception updateEventInfo()", e);
    }
  }

  /**
   * Saves a new event.
   *
   * @param event the EventDTO containing event data
   * @return a ResponseEntity containing the saved EventDTO
   * @throws EventException if the event cannot be saved
   */
  @PostMapping("/events")
  public ResponseEntity<EventDTO> saveEvent(@ModelAttribute EventDTO event) throws EventException {
    try {
      log.debug("Entering saveEvent() with event: {}", event);
      EventDTO savedEvent = eventRepo.saveEvent(event);
      log.debug("Saved event: {}", savedEvent);
      return ResponseEntity.ok(savedEvent);
    } catch (EventServiceException e) {
      log.error("Error in saveEvent()", e);
      throw new EventControllerException("Event Service Exception saveEvent()", e);
    }
  }

  /**
   * Retrieves detailed information of a specific event by its ID.
   *
   * @param id the ID of the event
   * @return a ResponseEntity containing the EventDTO
   * @throws EventException if the event details cannot be retrieved
   */
  @GetMapping("/event-details/{id}")
  public ResponseEntity<EventDTO> getEventDetails(@PathVariable int id) throws EventException {
    try {
      log.debug("Entering getEventDetails() with id: {}", id);
      EventDTO eventDetails = eventRepo.getEventDetails(id);
      log.debug("Retrieved event details: {}", eventDetails);
      return ResponseEntity.ok(eventDetails);
    } catch (EventServiceException e) {
      log.error("Error in getEventDetails() for id: {}", id, e);
      throw new EventControllerException("Event Service Exception getEventDetails()", e);
    }
  }

  /**
   * Endpoint to test error response.
   *
   * @return a String indicating an error
   * @throws EventException always throws an EventException
   */
  @GetMapping("/event-error")
  public String eventError() throws EventException {
    log.debug("Entering eventError()");
    throw new EventException("Event exception occurred!");
  }
}
