package com.example.wsa.quickRegister;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Entity representing a temporary volunteer registration.
 */
@Entity
@Table(name = "temporary_volunteers")
@Setter
@Getter
@ToString
public class QuickRegister {

  /**
   * Unique identifier for the temporary volunteer.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  /**
   * Identifier of the event for which the volunteer is registering.
   */
  private Integer eventID;

  /**
   * First name of the volunteer.
   */
  private String firstName;

  /**
   * Last name of the volunteer.
   */
  private String lastName;

  /**
   * Email address of the volunteer.
   */
  private String email;

  /**
   * Phone number of the volunteer.
   */
  private String phoneNumber;
}
