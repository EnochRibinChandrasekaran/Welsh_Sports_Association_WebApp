package com.example.wsa.organiser.exception;

/**
 * Exception thrown by the Organiser Service to indicate errors specific to service operations.
 * This exception extends from {@link OrganiserControllerException}
 * to maintain the exception hierarchy.
 */
public class OrganiserServiceException extends OrganiserControllerException {

  /**
   * Constructs a new OrganiserServiceException with {@code null} as its detail message.
   */
  public OrganiserServiceException() {
    super();
  }

  /**
   * Constructs a new OrganiserServiceException with the specified detail message.
   *
   * @param message the detail message
   */
  public OrganiserServiceException(String message) {
    super(message);
  }

  /**
   * Constructs a new OrganiserServiceException with the specified detail message and cause.
   *
   * @param message the detail message
   * @param cause   the cause of the exception
   */
  public OrganiserServiceException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Constructs a new OrganiserServiceException with the specified cause.
   *
   * @param cause the cause of the exception
   */
  public OrganiserServiceException(Throwable cause) {
    super(cause);
  }

  /**
   * Constructs a new OrganiserServiceException with the specified detail message, cause,
   * suppression enabled or disabled, and writable stack trace enabled or disabled.
   *
   * @param message            the detail message
   * @param cause              the cause of the exception
   * @param enableSuppression  whether suppression is enabled
   * @param writableStackTrace whether the stack trace should be writable
   */
  public OrganiserServiceException(String message, Throwable cause,
                                   boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
