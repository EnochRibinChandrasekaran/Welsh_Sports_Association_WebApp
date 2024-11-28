package com.example.wsa.volunteer.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * Base exception class for Volunteer-related exceptions.
 */
@Slf4j
public class VolunteerException extends Exception {

  /**
   * Default constructor.
   */
  public VolunteerException() {
    log.error("VolunteerException initialized without a message or cause.");
  }

  /**
   * Constructs a new VolunteerException with the specified detail message.
   *
   * @param message the detail message.
   */
  public VolunteerException(String message) {
    super(message);
    log.error("VolunteerException initialized with message: {}", message);
  }

  /**
   * Constructs a new VolunteerException with the specified cause.
   *
   * @param cause the cause of the exception.
   */
  public VolunteerException(Throwable cause) {
    super(cause);
    log.error("VolunteerException initialized with cause: {}", cause.toString());
  }

  /**
   * Constructs a new VolunteerException with the specified detail message and cause.
   *
   * @param message the detail message.
   * @param cause   the cause of the exception.
   */
  public VolunteerException(String message, Throwable cause) {
    super(message, cause);
    log.error("VolunteerException initialized with message: {} and cause: {}",
            message, cause.toString());
  }

  /**
   * Constructs a new VolunteerException with the specified detail message, cause,
   * suppression enabled or disabled, and writable stack trace enabled or disabled.
   *
   * @param message            the detail message.
   * @param cause              the cause of the exception.
   * @param enableSuppression  whether suppression is enabled or disabled.
   * @param writableStackTrace whether the stack trace should be writable.
   */
  public VolunteerException(String message, Throwable cause,
                            boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
    log.error("VolunteerException initialized with message: "
                    + "{}, cause: {}, enableSuppression: {}, writableStackTrace: {}",
            message, cause.toString(), enableSuppression, writableStackTrace);
  }
}
