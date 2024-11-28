package com.example.wsa.organiser.exception;

/**
 * Base exception class for all organiser-related exceptions.
 * This exception serves as the parent for more specific organiser exceptions.
 */
public class OrganiserException extends Exception {

  /**
   * Constructs a new OrganiserException with {@code null} as its detail message.
   */
  public OrganiserException() {
    super();
  }

  /**
   * Constructs a new OrganiserException with the specified detail message.
   *
   * @param message the detail message
   */
  public OrganiserException(String message) {
    super(message);
  }

  /**
   * Constructs a new OrganiserException with the specified detail message and cause.
   *
   * @param message the detail message
   * @param cause   the cause of the exception
   */
  public OrganiserException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Constructs a new OrganiserException with the specified cause.
   *
   * @param cause the cause of the exception
   */
  public OrganiserException(Throwable cause) {
    super(cause);
  }

  /**
   * Constructs a new OrganiserException with the specified detail message, cause,
   * suppression enabled or disabled, and writable stack trace enabled or disabled.
   *
   * @param message            the detail message
   * @param cause              the cause of the exception
   * @param enableSuppression  whether suppression is enabled
   * @param writableStackTrace whether the stack trace should be writable
   */
  public OrganiserException(String message, Throwable cause,
                            boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
