package com.example.wsa.event;

import com.example.wsa.event.exception.EventRepositoryException;
import com.example.wsa.event.exception.EventServiceException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Interface for event repository operations.
 */
public interface EventRepo {

  /**
   * Retrieves a paginated list of all events.
   *
   * @param pageable the pagination information
   * @return a Page of EventDTO objects
   */
  Page<EventDTO> getAllEvents(Pageable pageable);

  /**
   * Retrieves a specific event by its ID.
   *
   * @param id the ID of the event
   * @return the EventDTO object
   * @throws EventRepositoryException if the event is not found
   * @throws EventServiceException    if an error occurs during retrieval
   */
  EventDTO getEvent(int id) throws EventRepositoryException, EventServiceException;

  /**
   * Updates an existing event with the given ID.
   *
   * @param id    the ID of the event to update
   * @param event the EventDTO containing updated event data
   * @return the updated EventDTO
   * @throws EventServiceException if an error occurs during update
   */
  EventDTO updateEventInfo(int id, EventDTO event) throws EventServiceException;

  /**
   * Saves a new event.
   *
   * @param event the EventDTO containing event data
   * @return the saved EventDTO
   * @throws EventServiceException if an error occurs during save
   */
  EventDTO saveEvent(EventDTO event) throws EventServiceException;

  /**
   * Retrieves detailed information of a specific event by its ID.
   *
   * @param id the ID of the event
   * @return the EventDTO containing event details
   * @throws EventServiceException if an error occurs during retrieval
   */
  EventDTO getEventDetails(int id) throws EventServiceException;
}
