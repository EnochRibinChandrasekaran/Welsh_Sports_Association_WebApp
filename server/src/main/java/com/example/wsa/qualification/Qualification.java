package com.example.wsa.qualification;

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
 * Represents a qualification entity in the system.
 * This entity is mapped to the "qualifications" table in the database.
 */
@Entity
@Table(name = "qualifications")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class Qualification {

  /**
   * The unique identifier for the qualification.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  /**
   * The name of the qualification.
   */
  private String name;

  /**
   * The type of the qualification.
   */
  private QualificationType type;
}
