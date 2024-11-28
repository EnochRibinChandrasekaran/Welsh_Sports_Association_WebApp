package com.example.wsa.availability;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service implementation for managing availability records.
 */
@Service
@Slf4j
public class AvailabilityServiceImpl implements AvailabilityService {

  private final AvailabilityRepository availabilityRepository;

  /**
   * Constructs a new AvailabilityServiceImpl with the specified AvailabilityRepository.
   *
   * @param availabilityRepository the repository used to access availability data
   */
  @Autowired
  public AvailabilityServiceImpl(AvailabilityRepository availabilityRepository) {
    this.availabilityRepository = availabilityRepository;
  }

  /**
   * Retrieves all availability records from the database.
   *
   * @return a list of all availability records
   */
  @Override
  public List<Availability> getAllAvailabilities() {
    log.debug("Entering getAllAvailabilities()");
    List<Availability> availabilities = availabilityRepository.findAll();
    log.debug("Retrieved availabilities: {}", availabilities);
    return availabilities;
  }
}
