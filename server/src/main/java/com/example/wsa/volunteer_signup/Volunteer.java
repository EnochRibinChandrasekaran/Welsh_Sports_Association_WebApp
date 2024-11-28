package com.example.wsa.volunteer_signup;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data class representing a volunteer.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Volunteer {

  /**
   * Unique identifier for each volunteer.
   */
  private Long id;

  /**
   * Username for login.
   */
  private String username;

  /**
   * Password for login.
   */
  private String password;

  /**
   * First name of the volunteer.
   */
  private String firstName;

  /**
   * Last name of the volunteer.
   */
  private String lastName;

  /**
   * Email address.
   */
  private String email;

  // Optional fields

  /**
   * Phone number.
   */
  private String phoneNumber;

  /**
   * Date of birth.
   */
  private LocalDate dob;

  /**
   * Street address.
   */
  private String address;

  /**
   * Postal code.
   */
  private String postalCode;

  /**
   * Occupation.
   */
  private String occupation;

  /**
   * List of qualifications.
   */
  private String qualifications;

  /**
   * Availability.
   */
  private String availability;

  /**
   * Roles.
   */
  private String roles;

  /**
   * DBS check status.
   */
  private String dbs;

  /**
   * Accessibility enhancements.
   */
  private String accessibilityEnhancement;

  /**
   * About the volunteer.
   */
  private String about;

  /**
   * Rewards earned.
   */
  private Integer rewardsEarned;

  /**
   * Emergency contact's name.
   */
  private String emergencyContactName;

  /**
   * Emergency contact's phone number.
   */
  private String emergencyPhoneNumber;

  /**
   * Emergency contact's relationship.
   */
  private String emergencyRelationship;

  /**
   * Volunteer goals.
   */
  private String goals;

  /**
   * Volunteer interests.
   */
  private String interests;

  /**
   * References.
   */
  private String references;

  /**
   * Agreement to terms and policies.
   */
  private Boolean agreeToPolicies;

  /**
   * Member status.
   */
  private String memberStatus;

  /**
   * Volunteer rating.
   */
  private Float rating;

  /**
   * Membership level.
   */
  private String membershipLevel;

  /**
   * Events attended.
   */
  private Integer eventAttended;

  /**
   * Gender.
   */
  private String gender;

  /**
   * Volunteer image as a byte array.
   */
  private byte[] image;
}
