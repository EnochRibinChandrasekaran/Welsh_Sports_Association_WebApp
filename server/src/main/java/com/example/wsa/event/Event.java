package com.example.wsa.event;

import com.example.wsa.organiser.Organiser;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity class representing an event in the system.
 * Mapped to the "event" table in the database.
 */
@Entity
@Table(name = "event")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {

  /**
   * The unique identifier for the event.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  /**
   * The title of the event.
   */
  private String title;

  /**
   * A detailed description of the event.
   */
  private String description;

  /**
   * Indicates if a DBS check is required for the event.
   */
  private Boolean dbsRequired;

  /**
   * The address where the event takes place.
   */
  private String address;

  /**
   * The city where the event is located.
   */
  private String city;

  /**
   * The postal code of the event location.
   */
  private String postalCode;

  /**
   * A landmark near the event location.
   */
  private String landmark;

  /**
   * The roles needed for the event.
   */
  private String rolesNeeded;

  /**
   * The rewards offered for participating in the event.
   */
  private String rewardsOffering;

  /**
   * The image associated with the event, stored as a byte array.
   */
  @Lob
  @Column(name = "image", columnDefinition = "LONGBLOB")
  private byte[] image;

  /**
   * The date of the event.
   */
  private LocalDate date;

  /**
   * The start time of the event.
   */
  private LocalDateTime startTime;

  /**
   * The end time of the event.
   */
  private LocalDateTime endTime;

  /**
   * Any accessibility assistance provided at the event.
   */
  private String accessibilityAssistanceProvided;

  /**
   * The organiser associated with the event.
   */
  @ManyToOne
  @JoinColumn(name = "organiser_id", referencedColumnName = "id")
  private Organiser organiser;

  /**
   * Indicates if the event is approved.
   */
  private Boolean approved;
}
