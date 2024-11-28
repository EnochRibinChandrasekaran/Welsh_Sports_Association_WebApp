package com.example.wsa.quickRegister;

import org.springframework.http.ResponseEntity;

/**
 * Service interface for quick volunteer registration.
 */
public interface QuickRegisterService {

  /**
   * Quickly registers a volunteer for an event.
   *
   * @param quickRegisterDTO the data transfer object containing volunteer information
   * @return a ResponseEntity with a success message
   */
  ResponseEntity<String> quickRegisterVolunteer(QuickRegisterDTO quickRegisterDTO);
}
