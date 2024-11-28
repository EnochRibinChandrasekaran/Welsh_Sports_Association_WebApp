package com.example.wsa.availability;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing availability records.
 */
@RestController
@RequestMapping("api/availability")
@Slf4j
public class AvailabilityController {

  private final AvailabilityService availabilityService;

  /**
   * Constructs a new AvailabilityController with the specified AvailabilityService.
   *
   * @param availabilityService the service used to manage availability records
   */
  @Autowired
  public AvailabilityController(AvailabilityService availabilityService) {
    this.availabilityService = availabilityService;
  }

  /**
   * Retrieves all availability records.
   *
   * @return a list of all availability records
   * @throws AvailabilityException if an error occurs while fetching the data
   */
  @GetMapping
  public List<Availability> getAllAvailabilities() throws AvailabilityException {
    try {
      log.debug("Entering getAllAvailabilities()");
      List<Availability> availabilities = availabilityService.getAllAvailabilities();
      log.debug("Retrieved availabilities: {}", availabilities);
      return availabilities;
    } catch (Exception e) {
      log.error("Error fetching availability data", e);
      throw new AvailabilityException("Error fetching availability data", e);
    }
  }
}
