package com.example.wsa.occupation;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service implementation for managing occupations.
 */
@Service
@Slf4j
public class OccupationServiceImpl implements OccupationService {

  private final OccupationRepository occupationRepository;

  /**
   * Constructs a new OccupationServiceImpl with the specified OccupationRepository.
   *
   * @param occupationRepository the repository used to access occupation data
   */
  @Autowired
  public OccupationServiceImpl(OccupationRepository occupationRepository) {
    this.occupationRepository = occupationRepository;
  }

  /**
   * Retrieves all occupations from the database.
   *
   * @return a list of all occupations
   */
  @Override
  public List<Occupation> getAllOccupations() {
    log.debug("Entering getAllOccupations()");
    List<Occupation> occupations = occupationRepository.findAll();
    log.debug("Retrieved {} occupations", occupations.size());
    return occupations;
  }
}
