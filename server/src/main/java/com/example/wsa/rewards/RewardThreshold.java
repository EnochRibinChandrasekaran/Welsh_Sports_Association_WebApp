package com.example.wsa.rewards;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Represents a reward threshold, storing the image name and points required.
 */
@Data
@AllArgsConstructor
public class RewardThreshold {

  /**
   * The unique identifier of the reward threshold.
   */
  private Long id;

  /**
   * The name of the image associated with the reward.
   */
  private String imageName;

  /**
   * The points required for the reward to be applicable.
   */
  private int pointsRequired;
}
