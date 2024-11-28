package com.example.wsa.accessibilty;

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
 * Represents an accessibility enhancement in the system.
 * This entity is mapped to the "accessibility_enhancement" table in the database.
 */
@Entity
@Table(name = "accessibility_enhancement")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class Accessibility {

  /**
   * The unique identifier for the accessibility enhancement.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  /**
   * The name of the accessibility enhancement.
   */
  private String name;
}
