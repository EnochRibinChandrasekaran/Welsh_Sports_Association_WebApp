package com.example.wsa.event;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Event entities.
 */
@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {

  /**
   * Fetches upcoming approved events using a stored procedure.
   *
   * @return a list of event data
   */
  @Query(value = "CALL GetUpcomingEvents()", nativeQuery = true)
  List<Object[]> findUpcomingEvents();

  /**
   * Fetches approved events dates using a stored procedure.
   *
   * @return a list of event dates
   */
  @Query(value = "CALL GetApprovedEventsDates()", nativeQuery = true)
  List<Object[]> findApprovedEventsDates();

  /**
   * Fetches past approved events using a stored procedure.
   *
   * @return a list of past events
   */
  @Query(value = "CALL GetPastEvents()", nativeQuery = true)
  List<Object[]> getPastEvents();

  /**
   * Fetches event details by event ID using a stored procedure.
   *
   * @param eventId the ID of the event
   * @return a list of event details
   */
  @Procedure(procedureName = "GetEventDetailsByEventID")
  List<Object[]> getEventDetailsByEventID(@Param("event_id") Integer eventId);
}
