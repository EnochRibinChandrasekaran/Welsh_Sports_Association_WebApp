package com.example.wsa.quickRegister;

import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * Implementation of the QuickRegisterService interface.
 */
@Service
@Slf4j
public class QuickRegisterServiceImpl implements QuickRegisterService {

  @PersistenceContext
  private EntityManager entityManager;

  /**
   * Quickly registers a volunteer for an event using a stored procedure.
   *
   * @param quickRegisterDTO the data transfer object containing volunteer information
   * @return a ResponseEntity with a success message
   */
  @Override
  public ResponseEntity<String> quickRegisterVolunteer(QuickRegisterDTO quickRegisterDTO) {
    log.debug("Starting quick registration for volunteer: {}", quickRegisterDTO);

    try {
      // Create a stored procedure query
      StoredProcedureQuery query = entityManager
              .createStoredProcedureQuery("QuickRegisterVolunteer");

      // Register parameters (adjust the parameter types and
      // positions according to your stored procedure)
      query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN); // Event ID
      query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);  // First name
      query.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);  // Last name
      query.registerStoredProcedureParameter(4, String.class, ParameterMode.IN);  // Email
      query.registerStoredProcedureParameter(5, String.class, ParameterMode.IN);  // Phone number

      // Set the parameters
      query.setParameter(1, quickRegisterDTO.getEventID());
      query.setParameter(2, quickRegisterDTO.getFirstName());
      query.setParameter(3, quickRegisterDTO.getLastName());
      query.setParameter(4, quickRegisterDTO.getEmail());
      query.setParameter(5, quickRegisterDTO.getPhoneNumber());

      // Execute the stored procedure
      query.execute();

      log.info("Volunteer {} {} registered successfully for event ID {}",
              quickRegisterDTO.getFirstName(),
              quickRegisterDTO.getLastName(),
              quickRegisterDTO.getEventID());

      // Return success message
      return ResponseEntity.ok("Volunteer signed up successfully");
    } catch (Exception e) {
      log.error("Error during quick registration for volunteer: {}", quickRegisterDTO, e);
      return ResponseEntity.status(500).body("An error occurred during registration.");
    }
  }
}
