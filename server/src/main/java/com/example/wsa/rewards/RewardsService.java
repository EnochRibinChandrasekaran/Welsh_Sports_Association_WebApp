package com.example.wsa.rewards;

import java.util.List;

/**
 * Service interface for managing rewards.
 */
public interface RewardsService {

  /**
   * Updates the points for a volunteer based on their event participation.
   *
   * @param volunteer_id the ID of the volunteer
   */
  void update_points(long volunteer_id);

  /**
   * Retrieves the total points for a volunteer.
   *
   * @param volunteer_id the ID of the volunteer
   * @return the total points
   */
  Integer retrieve_point_total(long volunteer_id);

  /**
   * Retrieves the names of rewards applicable to the given point total.
   *
   * @param point_total the total points of the volunteer
   * @return a list of reward names
   */
  List<String> retrieve_applicable_reward_names(int point_total);
}
