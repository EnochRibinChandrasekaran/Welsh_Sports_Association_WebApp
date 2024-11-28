package com.example.wsa.event;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Data Transfer Object for Event data.
 */
@SuppressWarnings("checkstyle:AbbreviationAsWordInName")
@Setter
@Getter
@ToString
public class EventDTO {

  /**
   * The unique identifier of the event.
   */
  private Integer id;

  /**
   * The name/title of the event.
   */
  private String name;

  /**
   * The description of the event.
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
   * The list of roles needed for the event.
   */
  private List<String> rolesNeeded;

  /**
   * The rewards offered for participating in the event.
   */
  private String rewardsOffering;

  /**
   * The image associated with the event as a byte array.
   */
  private byte[] eventImage;

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
   * The list of accessibility assistance provided at the event.
   */
  private List<String> accessibilityAssistance;

  /**
   * Indicates if the event is approved.
   */
  private Boolean approved;

  /**
   * The day of the week for the event date.
   */
  private String dayOfWeek;

  /**
   * The volunteer associated with the event (if any).
   */
  private String volunteer;

  /**
   * The ID of the organiser of the event.
   */
  private int organiserID;

  /**
   * The name of the organiser of the event.
   */
  private String organiserName;

  /**
   * The base64 encoded string of the event image.
   */
  private String base64Image;

  /**
   * Default constructor.
   */
  public EventDTO() {
  }

  /**
   * Constructs an EventDTO with the specified parameters.
   *
   * @param id         the event ID
   * @param name       the event name
   * @param eventImage the event image as a byte array
   * @param postalCode the postal code of the event
   * @param city       the city where the event is located
   * @param date       the date of the event
   * @param dayOfWeek  the day of the week for the event date
   */
  public EventDTO(Integer id, String name,
                  byte[] eventImage, String postalCode,
                  String city, LocalDate date, String dayOfWeek) {
    this.id = id;
    this.name = name;
    this.city = city;
    this.postalCode = postalCode;
    this.eventImage = eventImage;
    this.date = date;
    this.dayOfWeek = dayOfWeek;
  }

  /**
   * Constructs an EventDTO with the specified parameters.
   *
   * @param id          the event ID
   * @param name        the event name
   * @param description the event description
   * @param address     the event address
   * @param landmark    a landmark near the event
   * @param date        the date of the event
   * @param startTime   the start time of the event
   * @param endTime     the end time of the event
   */
  public EventDTO(Integer id, String name,
                  String description, String address,
                  String landmark, LocalDate date,
                  LocalDateTime startTime, LocalDateTime endTime) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.address = address;
    this.landmark = landmark;
    this.date = date;
    this.startTime = startTime;
    this.endTime = endTime;
  }

  /**
   * Constructs an EventDTO with the specified parameters.
   *
   * @param id   the event ID
   * @param name the event name
   * @param date the date of the event
   */
  public EventDTO(Integer id, String name, LocalDate date) {
    this.id = id;
    this.name = name;
    this.date = date;
  }
}
