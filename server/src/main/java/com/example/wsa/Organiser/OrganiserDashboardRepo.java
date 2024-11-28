package com.example.wsa.organiser;

import com.example.wsa.event.EventDTO;
import java.util.List;

/**
 * Interface that defines the contract for retrieving events data related to
 * an organiser's dashboard. Implementing classes should provide mechanisms
 * to fetch upcoming and past events from a data source.
 */
public interface OrganiserDashboardRepo {

  /**
   * Retrieves a list of upcoming events for a specific organiser.
   *
   * @param organiserId the ID of the organiser
   * @return a list of EventDTO objects representing upcoming events
   */
  List<EventDTO> getUpcomingEvents(int organiserId);

  /**
   * Retrieves a list of past events for a specific organiser.
   *
   * @param organiserId the ID of the organiser
   * @return a list of EventDTO objects representing past events
   */
  List<EventDTO> getPastEvents(int organiserId);
}
