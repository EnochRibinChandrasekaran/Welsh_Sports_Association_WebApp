package com.example.wsa.organiser.exception;

/**
 * Exception thrown by the Organiser Repository
 * to indicate errors specific to repository operations.
 * This exception extends from {@link OrganiserServiceException}
 * to maintain the exception hierarchy.
 */
public class OrganiserRepositoryException extends OrganiserServiceException {

  /**
   * Constructs a new OrganiserRepositoryException with {@code null} as its detail message.
   */
  public OrganiserRepositoryException() {
    super();
  }

  /**
   * Constructs a new OrganiserRepositoryException with the specified detail message.
   *
   * @param message the detail message
   */
  public OrganiserRepositoryException(String message) {
    super(message);
  }

  /**
   * Constructs a new OrganiserRepositoryException with the specified detail message and cause.
   *
   * @param message the detail message
   * @param cause   the cause of the exception
   */
  public OrganiserRepositoryException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Constructs a new OrganiserRepositoryException with the specified cause.
   *
   * @param cause the cause of the exception
   */
  public OrganiserRepositoryException(Throwable cause) {
    super(cause);
  }

  /**
   * Constructs a new OrganiserRepositoryException with the specified detail message, cause,
   * suppression enabled or disabled, and writable stack trace enabled or disabled.
   *
   * @param message            the detail message
   * @param cause              the cause of the exception
   * @param enableSuppression  whether suppression is enabled
   * @param writableStackTrace whether the stack trace should be writable
   */
  public OrganiserRepositoryException(String message, Throwable cause,
                                      boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
