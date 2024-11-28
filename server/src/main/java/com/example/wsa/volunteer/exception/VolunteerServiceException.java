package com.example.wsa.volunteer.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * Exception thrown when there is an error in the Volunteer Service layer.
 */
@Slf4j
public class VolunteerServiceException extends VolunteerControllerException {

  /**
   * Default constructor.
   */
  public VolunteerServiceException() {
    log.error("VolunteerServiceException initialized without a message or cause.");
  }

  /**
   * Constructs a new VolunteerServiceException with the specified detail message.
   *
   * @param message the detail message.
   */
  public VolunteerServiceException(String message) {
    super(message);
    log.error("VolunteerServiceException initialized with message: {}", message);
  }

  /**
   * Constructs a new VolunteerServiceException with the specified cause.
   *
   * @param cause the cause of the exception.
   */
  public VolunteerServiceException(Throwable cause) {
    super(cause);
    log.error("VolunteerServiceException initialized with cause: {}", cause.toString());
  }

  /**
   * Constructs a new VolunteerServiceException with the specified detail message and cause.
   *
   * @param message the detail message.
   * @param cause   the cause of the exception.
   */
  public VolunteerServiceException(String message, Throwable cause) {
    super(message, cause);
    log.error("VolunteerServiceException initialized with message: {} and cause: {}",
            message, cause.toString());
  }

  /**
   * Constructs a new VolunteerServiceException with the specified detail message, cause,
   * suppression enabled or disabled, and writable stack trace enabled or disabled.
   *
   * @param message            the detail message.
   * @param cause              the cause of the exception.
   * @param enableSuppression  whether suppression is enabled or disabled.
   * @param writableStackTrace whether the stack trace should be writable.
   */
  public VolunteerServiceException(String message, Throwable cause,
                                   boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
    log.error("VolunteerServiceException initialized with message:"
                    + " {}, cause: {}, enableSuppression: {}, writableStackTrace: {}",
            message, cause.toString(), enableSuppression, writableStackTrace);
  }
}
