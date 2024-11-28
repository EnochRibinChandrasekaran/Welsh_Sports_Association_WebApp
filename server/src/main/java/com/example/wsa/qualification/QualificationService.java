package com.example.wsa.qualification;

import java.util.List;

/**
 * Service interface for qualification-related operations.
 */
public interface QualificationService {

  /**
   * Retrieves all qualifications.
   *
   * @return a list of all qualifications
   */
  List<Qualification> getAllQualifications();
}
