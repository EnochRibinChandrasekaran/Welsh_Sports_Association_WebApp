package com.example.wsa.occupation;

import java.util.List;

/**
 * Service interface for occupation-related operations.
 */
public interface OccupationService {

  /**
   * Retrieves all occupations.
   *
   * @return a list of all occupations
   */
  List<Occupation> getAllOccupations();
}
