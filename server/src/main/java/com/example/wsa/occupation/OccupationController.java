package com.example.wsa.occupation;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing occupation entities.
 */
@RestController
@RequestMapping("api/occupations")
@Slf4j
public class OccupationController {

  private final OccupationService occupationService;

  /**
   * Constructs a new OccupationController with the specified OccupationService.
   *
   * @param occupationService the service used to manage occupations
   */
  @Autowired
  public OccupationController(OccupationService occupationService) {
    this.occupationService = occupationService;
  }

  /**
   * Retrieves all occupations.
   *
   * @return a list of all occupations
   * @throws OccupationException if an error occurs while fetching occupations
   */
  @GetMapping
  public List<Occupation> getAllOccupations() throws OccupationException {
    try {
      log.debug("Entering getAllOccupations()");
      List<Occupation> occupations = occupationService.getAllOccupations();
      log.debug("Retrieved {} occupations", occupations.size());
      return occupations;
    } catch (Exception e) {
      log.error("Error fetching occupations", e);
      throw new OccupationException("Error Fetching Occupations", e);
    }
  }
}
