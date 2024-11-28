package com.example.wsa.organiser.exception;

/**
 * Exception thrown by the Organiser Controller
 * to indicate errors specific to controller operations.
 * This exception extends from {@link OrganiserException}
 * to provide a hierarchy of organiser-related exceptions.
 */
public class OrganiserControllerException extends OrganiserException {

  /**
   * Constructs a new OrganiserControllerException with {@code null} as its detail message.
   */
  public OrganiserControllerException() {
    super();
  }

  /**
   * Constructs a new OrganiserControllerException with the specified detail message.
   *
   * @param message the detail message
   */
  public OrganiserControllerException(String message) {
    super(message);
  }

  /**
   * Constructs a new OrganiserControllerException with the specified detail message and cause.
   *
   * @param message the detail message
   * @param cause   the cause of the exception
   */
  public OrganiserControllerException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Constructs a new OrganiserControllerException with the specified cause.
   *
   * @param cause the cause of the exception
   */
  public OrganiserControllerException(Throwable cause) {
    super(cause);
  }

  /**
   * Constructs a new OrganiserControllerException with the specified detail message, cause,
   * suppression enabled or disabled, and writable stack trace enabled or disabled.
   *
   * @param message            the detail message
   * @param cause              the cause of the exception
   * @param enableSuppression  whether suppression is enabled
   * @param writableStackTrace whether the stack trace should be writable
   */
  public OrganiserControllerException(String message, Throwable cause,
                                      boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}