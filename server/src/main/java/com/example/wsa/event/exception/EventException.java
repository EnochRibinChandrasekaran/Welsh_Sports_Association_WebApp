package com.example.wsa.event.exception;

/**
 * Base exception class for event-related errors in the application.
 */
public class EventException extends Exception {

  /**
   * Constructs a new EventException with {@code null} as its detail message.
   */
  public EventException() {
    super();
  }

  /**
   * Constructs a new EventException with the specified detail message.
   *
   * @param message the detail message
   */
  public EventException(String message) {
    super(message);
  }

  /**
   * Constructs a new EventException with the specified detail message and cause.
   *
   * @param message the detail message
   * @param cause   the cause of the exception
   */
  public EventException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Constructs a new EventException with the specified cause.
   *
   * @param cause the cause of the exception
   */
  public EventException(Throwable cause) {
    super(cause);
  }

  /**
   * Constructs a new EventException with the specified detail message, cause,
   * suppression enabled or disabled, and writable stack trace enabled or disabled.
   *
   * @param message            the detail message
   * @param cause              the cause of the exception
   * @param enableSuppression  whether suppression is enabled or disabled
   * @param writableStackTrace whether the stack trace should be writable
   */
  public EventException(String message, Throwable cause,
                        boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
