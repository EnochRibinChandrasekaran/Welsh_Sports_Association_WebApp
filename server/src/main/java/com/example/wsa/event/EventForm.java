package com.example.wsa.event;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

/**
 * Represents the data structure for an event form, including
 * fields for event details such as title, date, time, and location, as well as
 * validation annotations to ensure required fields are provided.
 */
@Setter
@Getter
public class EventForm {

  /**
   * The title of the event. This field is required and cannot be blank.
   */
  @NotBlank(message = "Event title is required")
  private String title;

  /**
   * The date of the event. This field is required and cannot be null.
   */
  @NotNull(message = "Event date is required")
  private Date date;

  /**
   * The start time of the event. This field is required and cannot be blank.
   */
  @NotBlank(message = "Start time is required")
  private String startTime;

  /**
   * The end time of the event. This field is required and cannot be blank.
   */
  @NotBlank(message = "End time is required")
  private String endTime;

  /**
   * A description of the event.
   */
  private String description = "";

  /**
   * The address where the event will take place.
   */
  private String address = "";

  /**
   * The city where the event will take place.
   */
  private String city = "";

  /**
   * The postal code for the event location.
   */
  private String postalCode = "";

  /**
   * A landmark near the event location.
   */
  private String landmark = "";

  /**
   * Indicates whether a DBS check is required for the event.
   */
  private Boolean dbsRequired = false;

  /**
   * Describes any accessibility assistance provided at the event.
   */
  private String accessibilityAssistanceProvided = "";

  /**
   * The main image for the event. This field is required and cannot be null.
   */
  @NotNull(message = "Main image is required")
  private MultipartFile mainImage;

  /**
   * The main image as a byte array after conversion.
   */
  private byte[] image;

  /**
   * A comma-separated string listing the roles needed for the event.
   */
  private String rolesNeeded = "";

  /**
   * A list of volunteers assigned to the event.
   */
  private List<String> volunteers;

  /**
   * A comma-separated string listing the rewards offered for participating in the event.
   */
  private String rewardsOffering = "";

  /**
   * Converts the uploaded image to a byte array and stores it.
   *
   * @throws IOException if an error occurs during the conversion
   */
  public void convertImageToBytes() throws IOException {
    if (this.mainImage != null && !this.mainImage.isEmpty()) {
      this.image = this.mainImage.getBytes(); // Store the image as a byte array
    }
  }
}
