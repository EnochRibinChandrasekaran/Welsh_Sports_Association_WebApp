package com.example.wsa.rewards;

import java.util.List;

/**
 * Repository interface for rewards-related database operations.
 */
public interface RewardsRepo {

  /**
   * Adds points to a volunteer for a specific event.
   *
   * @param volunteer_id the ID of the volunteer
   * @param event_id     the ID of the event
   * @param points       the amount of points to add
   */
  void add_points(long volunteer_id, long event_id, long points);

  /**
   * Returns a list of event IDs that a volunteer is registered for.
   *
   * @param volunteer_id the ID of the volunteer
   * @return a list of event IDs
   */
  List<Integer> return_registered_events_list(long volunteer_id);

  /**
   * Checks if a point allocation is already present for a volunteer and event.
   *
   * @param volunteer_id the ID of the volunteer
   * @param event_id     the ID of the event
   * @return true if the allocation exists, false otherwise
   */
  Boolean isAttemptedAllocationPresent(long volunteer_id, long event_id);

  /**
   * Retrieves the expiry date of an event.
   *
   * @param event_id the ID of the event
   * @return the expiry date as a String
   */
  String getEventExpiryDate(long event_id);

  /**
   * Retrieves the expiry time of an event.
   *
   * @param event_id the ID of the event
   * @return the expiry time as a String
   */
  String getEventExpiryTime(long event_id);

  /**
   * Retrieves the total points of a volunteer.
   *
   * @param volunteer_id the ID of the volunteer
   * @return the total points
   */
  Integer retrieve_point_total(long volunteer_id);

  /**
   * Retrieves all reward items with their thresholds.
   *
   * @return a list of RewardThreshold objects
   */
  List<RewardThreshold> retrieve_all_reward_items();
}
