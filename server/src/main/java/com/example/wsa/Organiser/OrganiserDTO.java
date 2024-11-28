package com.example.wsa.organiser;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for Organiser entity.
 * This class is used to transfer data between layers without exposing the entity directly.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganiserDTO {

  /**
   * The unique identifier for the organiser.
   */
  private Integer id;

  /**
   * The company name of the organiser.
   */
  private String companyName;

  /**
   * The logo of the organiser, stored as a byte array.
   */
  private byte[] logo;

  /**
   * The postal code of the organiser's address.
   */
  private String postCode;

  /**
   * Indicates whether the organiser is a charity.
   */
  private Boolean charity;

  /**
   * The address of the organiser.
   */
  private String address;

  /**
   * The telephone number of the organiser.
   */
  private String telephone;

  /**
   * The email address of the organiser.
   */
  private String email;

  /**
   * The website URL of the organiser.
   */
  private String website;

  /**
   * The name of the main contact person.
   */
  private String mainContactName;

  /**
   * The position of the main contact person.
   */
  private String mainContactPosition;

  /**
   * The mobile number of the main contact person.
   */
  private String mainContactMobileNumber;

  /**
   * The founding date of the organiser.
   */
  private LocalDate foundingDate;

  /**
   * The number of members in the organisation.
   */
  private Integer numberOfMembers;

  /**
   * Associated clubs with the organiser.
   */
  private String associatedClubs;

  /**
   * The turnover of the organiser.
   */
  private String turnover;

  /**
   * The membership category of the organiser.
   */
  private String membershipCategory;

  /**
   * The preferred language for communication.
   */
  private String preferredLanguage;

  /**
   * Indicates if the organiser wants to receive newsletters.
   */
  private Boolean newsletter;

  /**
   * Indicates if the organiser account is activated.
   */
  private Boolean activated;
}
