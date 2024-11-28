package com.example.wsa.availability;

/**
 * Custom exception class for handling availability-related exceptions.
 */
public class AvailabilityException extends RuntimeException {

  /**
   * Constructs a new AvailabilityException with {@code null} as its detail message.
   */
  public AvailabilityException() {
    super();
  }

  /**
   * Constructs a new AvailabilityException with the specified detail message.
   *
   * @param message the detail message
   */
  public AvailabilityException(String message) {
    super(message);
  }

  /**
   * Constructs a new AvailabilityException with the specified detail message and cause.
   *
   * @param message the detail message
   * @param cause   the cause
   */
  public AvailabilityException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Constructs a new AvailabilityException with the specified cause.
   *
   * @param cause the cause
   */
  public AvailabilityException(Throwable cause) {
    super(cause);
  }

  /**
   * Constructs a new AvailabilityException with the specified detail message, cause,
   * suppression enabled or disabled, and writable stack trace enabled or disabled.
   *
   * @param message            the detail message
   * @param cause              the cause
   * @param enableSuppression  whether suppression is enabled
   * @param writableStackTrace whether the stack trace should be writable
   */
  public AvailabilityException(String message, Throwable cause,
                               boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
