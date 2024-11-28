package com.example.wsa.exception;

import com.example.wsa.accessibilty.AccessibilityException;
import com.example.wsa.availability.AvailabilityException;
import com.example.wsa.event.exception.EventException;
import com.example.wsa.occupation.OccupationException;
import com.example.wsa.organiser.exception.OrganiserException;
import com.example.wsa.qualification.QualificationException;
import com.example.wsa.roles.RolesException;
import com.example.wsa.turnover.TurnoverException;
import com.example.wsa.volunteer.exception.VolunteerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * Global exception handler to catch and handle all exceptions in a consistent manner.
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  /**
   * Handles VolunteerException and returns a standardized error response.
   *
   * @param ex      the VolunteerException thrown
   * @param request the current web request
   * @return a ResponseEntity containing the error response and HTTP status
   */
  @ExceptionHandler(VolunteerException.class)
  public ResponseEntity<ErrorResponse> handleVolunteerException(
          VolunteerException ex, WebRequest request) {
    log.error("VolunteerException occurred: {}", ex.getMessage(), ex);
    return new ResponseEntity<>(
            new ErrorResponse("VOLUNTEER_EXCEPTION", ex.getMessage()),
            HttpStatus.INTERNAL_SERVER_ERROR);
  }

  /**
   * Handles OrganiserException and returns a standardized error response.
   *
   * @param ex      the OrganiserException thrown
   * @param request the current web request
   * @return a ResponseEntity containing the error response and HTTP status
   */
  @ExceptionHandler(OrganiserException.class)
  public ResponseEntity<ErrorResponse> handleOrganiserException(
          OrganiserException ex, WebRequest request) {
    log.error("OrganiserException occurred: {}", ex.getMessage(), ex);
    return new ResponseEntity<>(
            new ErrorResponse("ORGANISER_EXCEPTION", ex.getMessage()),
            HttpStatus.INTERNAL_SERVER_ERROR);
  }

  /**
   * Handles EventException and returns a standardized error response.
   *
   * @param ex      the EventException thrown
   * @param request the current web request
   * @return a ResponseEntity containing the error response and HTTP status
   */
  @ExceptionHandler(EventException.class)
  public ResponseEntity<ErrorResponse> handleEventException(
          EventException ex, WebRequest request) {
    log.error("EventException occurred: {}", ex.getMessage(), ex);
    return new ResponseEntity<>(
            new ErrorResponse("EVENT_EXCEPTION", ex.getMessage()),
            HttpStatus.INTERNAL_SERVER_ERROR);
  }

  /**
   * Handles AccessibilityException and returns a standardized error response.
   *
   * @param ex      the AccessibilityException thrown
   * @param request the current web request
   * @return a ResponseEntity containing the error response and HTTP status
   */
  @ExceptionHandler(AccessibilityException.class)
  public ResponseEntity<ErrorResponse> handleAccessibilityException(
          AccessibilityException ex, WebRequest request) {
    log.error("AccessibilityException occurred: {}", ex.getMessage(), ex);
    return new ResponseEntity<>(
            new ErrorResponse("ACCESSIBILITY_EXCEPTION", ex.getMessage()),
            HttpStatus.INTERNAL_SERVER_ERROR);
  }

  /**
   * Handles AvailabilityException and returns a standardized error response.
   *
   * @param ex      the AvailabilityException thrown
   * @param request the current web request
   * @return a ResponseEntity containing the error response and HTTP status
   */
  @ExceptionHandler(AvailabilityException.class)
  public ResponseEntity<ErrorResponse> handleAvailabilityException(
          AvailabilityException ex, WebRequest request) {
    log.error("AvailabilityException occurred: {}", ex.getMessage(), ex);
    return new ResponseEntity<>(
            new ErrorResponse("AVAILABILITY_EXCEPTION", ex.getMessage()),
            HttpStatus.INTERNAL_SERVER_ERROR);
  }

  /**
   * Handles OccupationException and returns a standardized error response.
   *
   * @param ex      the OccupationException thrown
   * @param request the current web request
   * @return a ResponseEntity containing the error response and HTTP status
   */
  @ExceptionHandler(OccupationException.class)
  public ResponseEntity<ErrorResponse> handleOccupationException(
          OccupationException ex, WebRequest request) {
    log.error("OccupationException occurred: {}", ex.getMessage(), ex);
    return new ResponseEntity<>(
            new ErrorResponse("OCCUPATION_EXCEPTION", ex.getMessage()),
            HttpStatus.INTERNAL_SERVER_ERROR);
  }

  /**
   * Handles QualificationException and returns a standardized error response.
   *
   * @param ex      the QualificationException thrown
   * @param request the current web request
   * @return a ResponseEntity containing the error response and HTTP status
   */
  @ExceptionHandler(QualificationException.class)
  public ResponseEntity<ErrorResponse> handleQualificationException(
          QualificationException ex, WebRequest request) {
    log.error("QualificationException occurred: {}", ex.getMessage(), ex);
    return new ResponseEntity<>(
            new ErrorResponse("QUALIFICATION_EXCEPTION", ex.getMessage()),
            HttpStatus.INTERNAL_SERVER_ERROR);
  }

  /**
   * Handles RolesException and returns a standardized error response.
   *
   * @param ex      the RolesException thrown
   * @param request the current web request
   * @return a ResponseEntity containing the error response and HTTP status
   */
  @ExceptionHandler(RolesException.class)
  public ResponseEntity<ErrorResponse> handleRolesException(
          RolesException ex, WebRequest request) {
    log.error("RolesException occurred: {}", ex.getMessage(), ex);
    return new ResponseEntity<>(
            new ErrorResponse("ROLES_EXCEPTION", ex.getMessage()),
            HttpStatus.INTERNAL_SERVER_ERROR);
  }

  /**
   * Handles TurnoverException and returns a standardized error response.
   *
   * @param ex      the TurnoverException thrown
   * @param request the current web request
   * @return a ResponseEntity containing the error response and HTTP status
   */
  @ExceptionHandler(TurnoverException.class)
  public ResponseEntity<ErrorResponse> handleTurnoverException(
          TurnoverException ex, WebRequest request) {
    log.error("TurnoverException occurred: {}", ex.getMessage(), ex);
    return new ResponseEntity<>(
            new ErrorResponse("TURNOVER_EXCEPTION", ex.getMessage()),
            HttpStatus.INTERNAL_SERVER_ERROR);
  }

  /**
   * Handles all RuntimeExceptions not specifically handled by other methods.
   *
   * @param ex      the RuntimeException thrown
   * @param request the current web request
   * @return a ResponseEntity containing the error response and HTTP status
   */
  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<ErrorResponse> handleRuntimeException(
          RuntimeException ex, WebRequest request) {
    Throwable rootCause = getRootCause(ex);
    log.error("RuntimeException occurred: {}", rootCause.getMessage(), ex);
    return new ResponseEntity<>(
            new ErrorResponse("INTERNAL_SERVER_ERROR", rootCause.getMessage()),
            HttpStatus.INTERNAL_SERVER_ERROR);
  }

  /**
   * Handles all generic Exceptions not specifically handled by other methods.
   *
   * @param ex      the Exception thrown
   * @param request the current web request
   * @return a ResponseEntity containing the error response and HTTP status
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleGlobalException(
          Exception ex, WebRequest request) {
    Throwable rootCause = getRootCause(ex);
    log.error("Unhandled exception occurred: {}", rootCause.getMessage(), ex);
    return new ResponseEntity<>(
            new ErrorResponse("GLOBAL_EXCEPTION", rootCause.getMessage()),
            HttpStatus.INTERNAL_SERVER_ERROR);
  }

  /**
   * Retrieves the root cause of the exception.
   *
   * @param ex the throwable from which to get the root cause
   * @return the root cause of the throwable
   */
  private Throwable getRootCause(Throwable ex) {
    Throwable cause = ex;
    while (cause.getCause() != null && cause != cause.getCause()) {
      cause = cause.getCause();
    }
    return cause;
  }

}
