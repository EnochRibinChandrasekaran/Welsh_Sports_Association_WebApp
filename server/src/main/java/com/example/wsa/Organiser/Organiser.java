package com.example.wsa.organiser;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Represents an organiser entity in the system.
 * This entity is mapped to the "organiser" table in the database.
 */
@Entity
@Table(name = "organiser")
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Organiser {

  /**
   * The unique identifier for the organiser.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  /**
   * The name of the company.
   */
  private String companyName;

  /**
   * The logo of the company, stored as a byte array.
   */
  @Lob
  @Column(name = "logo", columnDefinition = "LONGBLOB")
  private byte[] logo;

  /**
   * The postal code of the company.
   */
  private String postCode;

  /**
   * Indicates whether the company is a charity.
   */
  private Boolean charity;

  /**
   * The address of the company.
   */
  private String address;

  /**
   * The telephone number of the company.
   */
  private String telephone;

  /**
   * The email address of the company.
   */
  private String email;

  /**
   * The website of the company.
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
   * The founding date of the company.
   */
  private LocalDate foundingDate;

  /**
   * The number of members in the company.
   */
  private Integer numberOfMembers;

  /**
   * The associated clubs with the company.
   */
  private String associatedClubs;

  /**
   * The turnover of the company.
   */
  private String turnover;

  /**
   * The membership category of the company.
   */
  private String membershipCategory;

  /**
   * The preferred language of the company.
   */
  private String preferredLanguage;

  /**
   * Indicates whether the company wants to receive newsletters.
   */
  private Boolean newsletter;

  /**
   * Indicates whether the organiser account is activated.
   */
  private Boolean activated;
}
