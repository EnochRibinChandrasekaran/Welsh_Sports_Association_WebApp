package com.example.wsa.rewards;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;

/**
 * Entity class representing a reward in the system.
 * This entity is mapped to the "reward" table in the database.
 */
@Entity
@Table(name = "reward")
public class Rewards {

  /**
   * The unique identifier of the reward.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  /**
   * The date associated with the reward.
   */
  private LocalDate date;

  /**
   * The image associated with the reward.
   */
  private String image;

  /**
   * The description of the reward.
   */
  private String description;
}
