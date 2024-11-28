package com.example.wsa.availability;

import java.util.List;

/**
 * Service interface for availability-related operations.
 */
public interface AvailabilityService {

  /**
   * Retrieves all availability records.
   *
   * @return a list of all availability records
   */
  List<Availability> getAllAvailabilities();
}
