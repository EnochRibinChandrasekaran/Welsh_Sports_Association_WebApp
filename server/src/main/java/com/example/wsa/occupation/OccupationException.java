package com.example.wsa.occupation;

/**
 * Custom exception class for handling occupation-related exceptions.
 */
public class OccupationException extends RuntimeException {

  /**
   * Constructs a new OccupationException with {@code null} as its detail message.
   */
  public OccupationException() {
    super();
  }

  /**
   * Constructs a new OccupationException with the specified detail message.
   *
   * @param message the detail message
   */
  public OccupationException(String message) {
    super(message);
  }

  /**
   * Constructs a new OccupationException with the specified detail message and cause.
   *
   * @param message the detail message
   * @param cause   the cause
   */
  public OccupationException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Constructs a new OccupationException with the specified cause.
   *
   * @param cause the cause
   */
  public OccupationException(Throwable cause) {
    super(cause);
  }

  /**
   * Constructs a new OccupationException with the specified detail message, cause,
   * suppression enabled or disabled, and writable stack trace enabled or disabled.
   *
   * @param message            the detail message
   * @param cause              the cause
   * @param enableSuppression  whether suppression is enabled
   * @param writableStackTrace whether the stack trace should be writable
   */
  public OccupationException(String message, Throwable cause,
                             boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
