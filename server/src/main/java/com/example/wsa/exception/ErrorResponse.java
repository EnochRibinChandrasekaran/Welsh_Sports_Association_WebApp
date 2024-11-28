package com.example.wsa.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents an error response sent back to the client in case of exceptions.
 */
@Setter
@Getter
public class ErrorResponse {

  /**
   * The error code representing the type of error.
   */
  private String errorCode;

  /**
   * A descriptive error message.
   */
  private String errorMessage;

  /**
   * Constructs a new ErrorResponse with the specified error code and message.
   *
   * @param errorCode    the error code representing the type of error
   * @param errorMessage the descriptive error message
   */
  public ErrorResponse(String errorCode, String errorMessage) {
    this.errorCode = errorCode;
    this.errorMessage = errorMessage;
  }

}
