package com.example.wsa.event;

import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller to handle HTTP requests related to event forms.
 * Provides endpoints to create, update, and retrieve event data.
 */
@RestController
@RequestMapping("/api")
@Validated
@Slf4j
public class EventFormController {

  /**
   * The EventFormJDBC instance used to interact with the database.
   */
  @Autowired
  private EventFormJDBC eventFormJDBC;

  /**
   * Converts an EventForm object to an EventDTO object.
   *
   * @param eventForm the EventForm object to be converted
   * @return the converted EventDTO object
   */
  private EventDTO convertToDTO(EventForm eventForm) {
    EventDTO eventDTO = new EventDTO();

    // Set basic fields
    eventDTO.setName(eventForm.getTitle());
    eventDTO.setDescription(eventForm.getDescription());
    eventDTO.setAddress(eventForm.getAddress());
    eventDTO.setCity(eventForm.getCity());
    eventDTO.setPostalCode(eventForm.getPostalCode());
    eventDTO.setLandmark(eventForm.getLandmark());
    eventDTO.setDbsRequired(eventForm.getDbsRequired());

    eventDTO.setAccessibilityAssistance(eventForm.getAccessibilityAssistanceProvided().isEmpty()
            ? Arrays.asList()
            : Arrays.asList(eventForm.getAccessibilityAssistanceProvided().split(",")));
    eventDTO.setRolesNeeded(eventForm.getRolesNeeded().isEmpty()
            ? Arrays.asList()
            : Arrays.asList(eventForm.getRolesNeeded().split(",")));

    eventDTO.setRewardsOffering(eventForm.getRewardsOffering());

    // Handle the date and time components
    if (eventForm.getDate() != null) {
      eventDTO.setDate(eventForm.getDate()
              .toInstant()
              .atZone(ZoneId.systemDefault()).toLocalDate());

      if (eventForm.getStartTime() != null && !eventForm.getStartTime().isEmpty()) {
        eventDTO.setStartTime(
                LocalDateTime.of(eventDTO.getDate(), LocalDateTime.parse("1970-01-01T"
                        + eventForm.getStartTime()).toLocalTime())
        );
      }

      if (eventForm.getEndTime() != null && !eventForm.getEndTime().isEmpty()) {
        eventDTO.setEndTime(
                LocalDateTime.of(eventDTO.getDate(), LocalDateTime.parse("1970-01-01T"
                        + eventForm.getEndTime()).toLocalTime())
        );
      }
    }

    // Handle the image if present
    if (eventForm.getImage() != null) {
      eventDTO.setEventImage(eventForm.getImage());
    }

    return eventDTO;
  }

  /**
   * Endpoint to handle PUT requests for updating existing events.
   *
   * @param id        the ID of the event to be updated
   * @param eventForm the EventForm object containing the updated event data
   * @return a ResponseEntity with a success or error message
   */
  @PutMapping("/events/{id}")
  public ResponseEntity<String> updateEvent(@PathVariable int id,
                                            @Valid @ModelAttribute EventForm eventForm) {
    try {
      log.debug("Entering updateEvent() with id: {}, eventForm: {}", id, eventForm);
      eventForm.convertImageToBytes(); // Convert image to byte array
      EventDTO eventDTO = convertToDTO(eventForm); // Convert EventForm to EventDTO
      eventFormJDBC.updateEvent(id, eventDTO); // Update event data in the database
      log.debug("Event updated successfully with id: {}", id);
      return new ResponseEntity<>("Event updated successfully", HttpStatus.OK);
    } catch (Exception e) {
      log.error("Error updating event with id: {}", id, e);
      return new ResponseEntity<>("Error updating event: "
              + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * Endpoint to handle GET requests for fetching an event by its ID.
   *
   * @param id the ID of the event to retrieve
   * @return a ResponseEntity containing the EventDTO if found, or a NOT_FOUND status if not found
   */
  @GetMapping("/events/{id}")
  public ResponseEntity<EventDTO> getEventById(@PathVariable int id) {
    try {
      log.debug("Entering getEventById() with id: {}", id);
      Optional<EventDTO> eventDTO = eventFormJDBC.getEventById(id);
      if (eventDTO.isPresent()) {
        log.debug("Event found with id: {}", id);
        return new ResponseEntity<>(eventDTO.get(), HttpStatus.OK);
      } else {
        log.warn("Event not found with id: {}", id);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    } catch (Exception e) {
      log.error("Error fetching event with id: {}", id, e);
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
