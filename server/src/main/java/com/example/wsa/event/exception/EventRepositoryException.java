package com.example.wsa.event.exception;

/**
 * Exception thrown by the Event Repository when an error occurs during data access operations.
 */
public class EventRepositoryException extends EventServiceException {

  /**
   * Constructs a new EventRepositoryException with the specified detail message.
   *
   * @param message the detail message
   */
  public EventRepositoryException(String message) {
    super(message);
  }

  /**
   * Constructs a new EventRepositoryException with the specified detail message and cause.
   *
   * @param message the detail message
   * @param cause   the cause of the exception
   */
  public EventRepositoryException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Constructs a new EventRepositoryException with the specified cause.
   *
   * @param cause the cause of the exception
   */
  public EventRepositoryException(Throwable cause) {
    super(cause);
  }

  /**
   * Constructs a new EventRepositoryException with the specified detail message, cause,
   * suppression enabled or disabled, and writable stack trace enabled or disabled.
   *
   * @param message            the detail message
   * @param cause              the cause of the exception
   * @param enableSuppression  whether suppression is enabled or disabled
   * @param writableStackTrace whether the stack trace should be writable
   */
  public EventRepositoryException(String message,
                                  Throwable cause,
                                  boolean enableSuppression,
                                  boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}

