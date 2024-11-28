package com.example.wsa.volunteer;

import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Data Transfer Object for Volunteer entity.
 * This class is used to transfer volunteer data between different layers of the application.
 */
@Setter
@Getter
@AllArgsConstructor
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class VolunteerDTO {

  /**
   * The unique identifier of the volunteer.
   */
  private Integer id;

  /**
   * The first name of the volunteer.
   */
  private String firstName;

  /**
   * The last name of the volunteer.
   */
  private String lastName;

  /**
   * The gender of the volunteer.
   */
  private String gender;

  /**
   * The date of birth of the volunteer.
   */
  private LocalDate dob;

  /**
   * The email address of the volunteer.
   */
  private String email;

  /**
   * The phone number of the volunteer.
   */
  private String phoneNumber;

  /**
   * The residential address of the volunteer.
   */
  private String address;

  /**
   * The postal code of the volunteer's address.
   */
  private String postalCode;

  /**
   * The occupation of the volunteer.
   */
  private String occupation;

  /**
   * The list of qualifications of the volunteer.
   */
  private List<String> qualifications;

  /**
   * The availability of the volunteer.
   */
  private List<String> availability;

  /**
   * The roles the volunteer is interested in or assigned to.
   */
  private List<String> roles;

  /**
   * The DBS (Disclosure and Barring Service) check status of the volunteer.
   */
  private String dbs;

  /**
   * The accessibility enhancements required by the volunteer.
   */
  private List<String> accessibilityEnhancement;

  /**
   * Additional information about the volunteer.
   */
  private String about;

  /**
   * The rewards earned by the volunteer.
   */
  private Integer rewardsEarned;

  /**
   * The name of the volunteer's emergency contact.
   */
  private String emergencyContactName;

  /**
   * The phone number of the volunteer's emergency contact.
   */
  private String emergencyPhoneNumber;

  /**
   * The relationship of the emergency contact to the volunteer.
   */
  private String emergencyRelationship;

  /**
   * The membership status of the volunteer.
   */
  private String memberStatus;

  /**
   * The rating of the volunteer.
   */
  private Float rating;

  /**
   * The number of events attended by the volunteer.
   */
  private Integer eventAttended;

  /**
   * The membership level of the volunteer.
   */
  private String membership;

  /**
   * The profile image of the volunteer stored as a byte array.
   */
  private byte[] image;
}
