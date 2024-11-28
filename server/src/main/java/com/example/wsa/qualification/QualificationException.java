package com.example.wsa.qualification;

/**
 * Custom exception class for handling qualification-related exceptions.
 */
public class QualificationException extends RuntimeException {

  /**
   * Constructs a new QualificationException with {@code null} as its detail message.
   */
  public QualificationException() {
    super();
  }

  /**
   * Constructs a new QualificationException with the specified detail message.
   *
   * @param message the detail message
   */
  public QualificationException(String message) {
    super(message);
  }

  /**
   * Constructs a new QualificationException with the specified detail message and cause.
   *
   * @param message the detail message
   * @param cause   the cause
   */
  public QualificationException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Constructs a new QualificationException with the specified cause.
   *
   * @param cause the cause
   */
  public QualificationException(Throwable cause) {
    super(cause);
  }

  /**
   * Constructs a new QualificationException with the specified detail message, cause,
   * suppression enabled or disabled, and writable stack trace enabled or disabled.
   *
   * @param message            the detail message
   * @param cause              the cause
   * @param enableSuppression  whether suppression is enabled
   * @param writableStackTrace whether the stack trace should be writable
   */
  public QualificationException(String message, Throwable cause,
                                boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
