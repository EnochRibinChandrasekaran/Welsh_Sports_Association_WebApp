package com.example.wsa.event.exception;

/**
 * Exception thrown by the Event Controller when an error occurs during request processing.
 */
public class EventControllerException extends EventException {

  /**
   * Constructs a new EventControllerException with the specified detail message.
   *
   * @param message the detail message
   */
  public EventControllerException(String message) {
    super(message);
  }

  /**
   * Constructs a new EventControllerException with the specified detail message and cause.
   *
   * @param message the detail message
   * @param cause   the cause of the exception
   */
  public EventControllerException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Constructs a new EventControllerException with the specified cause.
   *
   * @param cause the cause of the exception
   */
  public EventControllerException(Throwable cause) {
    super(cause);
  }

  /**
   * Constructs a new EventControllerException with the specified detail message, cause,
   * suppression enabled or disabled, and writable stack trace enabled or disabled.
   *
   * @param message            the detail message
   * @param cause              the cause of the exception
   * @param enableSuppression  whether suppression is enabled or disabled
   * @param writableStackTrace whether the stack trace should be writable
   */
  public EventControllerException(String message,
                                  Throwable cause,
                                  boolean enableSuppression,
                                  boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
