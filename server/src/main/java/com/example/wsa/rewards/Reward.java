package com.example.wsa.rewards;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object representing a reward.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reward {

  /**
   * The unique identifier of the reward.
   */
  private Long id;

  /**
   * The points associated with the reward.
   */
  private int points;

  /**
   * The event associated with the reward.
   */
  private String event;

  /**
   * The date when the reward was granted or is valid until.
   */
  private LocalDate date;
}
