package com.example.wsa.occupation;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Represents an occupation entity in the system.
 * This entity is mapped to the "occupations" table in the database.
 */
@Entity
@Table(name = "occupations")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class Occupation {

  /**
   * The unique identifier for the occupation.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  /**
   * The name of the occupation.
   */
  private String name;

  /**
   * The category to which the occupation belongs.
   */
  private String category;
}
