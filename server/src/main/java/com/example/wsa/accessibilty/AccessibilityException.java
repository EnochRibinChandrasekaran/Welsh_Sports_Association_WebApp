package com.example.wsa.accessibilty;

/**
 * Custom exception class for handling accessibility-related exceptions.
 */
public class AccessibilityException extends RuntimeException {

  /**
   * Constructs a new AccessibilityException with {@code null} as its detail message.
   */
  public AccessibilityException() {
    super();
  }

  /**
   * Constructs a new AccessibilityException with the specified detail message.
   *
   * @param message the detail message
   */
  public AccessibilityException(String message) {
    super(message);
  }

  /**
   * Constructs a new AccessibilityException with the specified detail message and cause.
   *
   * @param message the detail message
   * @param cause   the cause
   */
  public AccessibilityException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Constructs a new AccessibilityException with the specified cause.
   *
   * @param cause the cause
   */
  public AccessibilityException(Throwable cause) {
    super(cause);
  }

  /**
   * Constructs a new AccessibilityException with the specified detail message, cause,
   * suppression enabled or disabled, and writable stack trace enabled or disabled.
   *
   * @param message            the detail message
   * @param cause              the cause
   * @param enableSuppression  whether suppression is enabled
   * @param writableStackTrace whether the stack trace should be writable
   */
  public AccessibilityException(String message, Throwable cause,
                                boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
