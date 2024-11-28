package com.example.wsa.volunteer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Represents a volunteer entity in the system.
 * This entity is mapped to the "volunteer" table in the database
 * and contains all relevant information about a volunteer.
 */
@Entity
@Table(name = "volunteer")
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class Volunteer {

  /**
   * The unique identifier for the volunteer.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
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
   * The address of the volunteer.
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
   * The qualifications of the volunteer.
   */
  private String qualifications;

  /**
   * The availability of the volunteer.
   */
  private String availability;

  /**
   * The roles the volunteer is interested in or assigned to.
   */
  private String roles;

  /**
   * Indicates whether the volunteer has a DBS (Disclosure and Barring Service) check.
   */
  private String dbs;

  /**
   * Any accessibility enhancements required by the volunteer.
   */
  private String accessibilityEnhancement;

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
   * The rating of the volunteer based on performance or feedback.
   */
  private Float rating;

  /**
   * The number of events the volunteer has attended.
   */
  private Integer eventAttended;

  /**
   * The image of the volunteer stored as a byte array.
   */
  @Lob
  @Column(name = "image", columnDefinition = "LONGBLOB")
  private byte[] image;

  /**
   * The membership level of the volunteer.
   */
  private String membershipLevel;

}
