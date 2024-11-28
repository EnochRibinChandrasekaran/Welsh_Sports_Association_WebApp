package com.example.wsa.volunteer.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * Exception thrown when there is an error in the Volunteer Controller layer.
 */
@Slf4j
public class VolunteerControllerException extends VolunteerException {

  /**
   * Default constructor.
   */
  public VolunteerControllerException() {
    log.error("VolunteerControllerException initialized without a message or cause.");
  }

  /**
   * Constructs a new VolunteerControllerException with the specified detail message.
   *
   * @param message the detail message.
   */
  public VolunteerControllerException(String message) {
    super(message);
    log.error("VolunteerControllerException initialized with message: {}", message);
  }

  /**
   * Constructs a new VolunteerControllerException with the specified cause.
   *
   * @param cause the cause of the exception.
   */
  public VolunteerControllerException(Throwable cause) {
    super(cause);
    log.error("VolunteerControllerException initialized with cause: {}", cause.toString());
  }

  /**
   * Constructs a new VolunteerControllerException with the specified detail message and cause.
   *
   * @param message the detail message.
   * @param cause   the cause of the exception.
   */
  public VolunteerControllerException(String message, Throwable cause) {
    super(message, cause);
    log.error("VolunteerControllerException initialized with message: {} and cause: {}",
            message, cause.toString());
  }

  /**
   * Constructs a new VolunteerControllerException with the specified detail message, cause,
   * suppression enabled or disabled, and writable stack trace enabled or disabled.
   *
   * @param message            the detail message.
   * @param cause              the cause of the exception.
   * @param enableSuppression  whether suppression is enabled or disabled.
   * @param writableStackTrace whether the stack trace should be writable.
   */
  public VolunteerControllerException(String message, Throwable cause,
                                      boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
    log.error("VolunteerControllerException initialized with message:"
                    + " {}, cause: {}, enableSuppression: {}, writableStackTrace: {}",
            message, cause.toString(), enableSuppression, writableStackTrace);
  }
}
