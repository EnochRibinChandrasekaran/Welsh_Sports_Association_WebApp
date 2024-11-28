package com.example.wsa.rewards;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing rewards.
 */
@RestController
@RequestMapping("/rewards")
@Slf4j
public class RewardsController {

  private final RewardsService rewardsService;

  /**
   * Constructs a new RewardsController with the specified RewardsService.
   *
   * @param rewardsService the service used to manage rewards
   */
  public RewardsController(RewardsService rewardsService) {
    this.rewardsService = rewardsService;
  }

  /**
   * Retrieves the total points for a user.
   *
   * @param userId the ID of the user
   * @return the total points as a ResponseEntity
   */
  @GetMapping("/getPoints")
  public ResponseEntity<Integer> getPoints(@RequestParam Long userId) {
    try {
      log.debug("Entering getPoints() with userId: {}", userId);
      rewardsService.update_points(userId);
      int points = rewardsService.retrieve_point_total(userId);
      log.debug("Retrieved points for userId {}: {}", userId, points);
      return ResponseEntity.ok(points);
    } catch (Exception e) {
      log.error("Error in getPoints(): ", e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
  }

  /**
   * Retrieves the list of applicable rewards based on total points.
   *
   * @param total_points the total points of the user
   * @return a list of applicable reward names as a ResponseEntity
   */
  @GetMapping("/getRewards")
  public ResponseEntity<List<String>> getApplicableRewards(@RequestParam int total_points) {
    try {
      log.debug("Entering getApplicableRewards() with total_points: {}", total_points);
      List<String> applicableRewardNames = rewardsService
              .retrieve_applicable_reward_names(total_points);
      log.debug("Retrieved applicable rewards: {}", applicableRewardNames);
      return ResponseEntity.ok(applicableRewardNames);
    } catch (Exception e) {
      log.error("Error in getApplicableRewards(): ", e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
  }
}
