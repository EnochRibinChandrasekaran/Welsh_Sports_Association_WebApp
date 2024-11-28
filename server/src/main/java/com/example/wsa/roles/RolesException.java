package com.example.wsa.roles;

/**
 * Custom exception class for handling role-related exceptions.
 */
public class RolesException extends RuntimeException {

  /**
   * Constructs a new RolesException with {@code null} as its detail message.
   */
  public RolesException() {
    super();
  }

  /**
   * Constructs a new RolesException with the specified detail message.
   *
   * @param message the detail message
   */
  public RolesException(String message) {
    super(message);
  }

  /**
   * Constructs a new RolesException with the specified detail message and cause.
   *
   * @param message the detail message
   * @param cause   the cause
   */
  public RolesException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Constructs a new RolesException with the specified cause.
   *
   * @param cause the cause
   */
  public RolesException(Throwable cause) {
    super(cause);
  }

  /**
   * Constructs a new RolesException with the specified detail message, cause,
   * suppression enabled or disabled, and writable stack trace enabled or disabled.
   *
   * @param message            the detail message
   * @param cause              the cause
   * @param enableSuppression  whether suppression is enabled
   * @param writableStackTrace whether the stack trace should be writable
   */
  public RolesException(String message, Throwable cause,
                        boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
