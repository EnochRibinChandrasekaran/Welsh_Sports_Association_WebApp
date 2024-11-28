package com.example.wsa.quickRegister;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Data Transfer Object for quick volunteer registration.
 */
@Setter
@Getter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class QuickRegisterDTO {

  /**
   * Unique identifier for the temporary volunteer registration.
   */
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

  /**
   * Date of birth of the volunteer.
   */
  private LocalDate dob;
}
