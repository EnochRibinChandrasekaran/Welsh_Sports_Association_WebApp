package com.example.wsa.turnover;

/**
 * Custom exception class for handling turnover-related exceptions.
 */
public class TurnoverException extends RuntimeException {

  /**
   * Constructs a new TurnoverException with {@code null} as its detail message.
   */
  public TurnoverException() {
    super();
  }

  /**
   * Constructs a new TurnoverException with the specified detail message.
   *
   * @param message the detail message
   */
  public TurnoverException(String message) {
    super(message);
  }

  /**
   * Constructs a new TurnoverException with the specified detail message and cause.
   *
   * @param message the detail message
   * @param cause   the cause
   */
  public TurnoverException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Constructs a new TurnoverException with the specified cause.
   *
   * @param cause the cause
   */
  public TurnoverException(Throwable cause) {
    super(cause);
  }

  /**
   * Constructs a new TurnoverException with the specified detail message, cause,
   * suppression enabled or disabled, and writable stack trace enabled or disabled.
   *
   * @param message            the detail message
   * @param cause              the cause
   * @param enableSuppression  whether suppression is enabled
   * @param writableStackTrace whether the stack trace should be writable
   */
  public TurnoverException(String message, Throwable cause,
                           boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
