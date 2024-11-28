package com.example.wsa.turnover;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service implementation for managing Turnover entities.
 */
@Service
@Slf4j
public class TurnoverServiceImpl implements TurnoverService {

  private final TurnoverRepository turnoverRepository;

  /**
   * Constructs a new TurnoverServiceImpl with the given TurnoverRepository.
   *
   * @param turnoverRepository the repository used to access turnover data
   */
  @Autowired
  public TurnoverServiceImpl(TurnoverRepository turnoverRepository) {
    this.turnoverRepository = turnoverRepository;
  }

  /**
   * Retrieves all turnover records from the database.
   *
   * @return a list of all turnovers
   */
  @Override
  public List<Turnover> getAllTurnovers() {
    log.debug("Entering TurnoverServiceImpl.getAllTurnovers()");
    List<Turnover> turnovers = turnoverRepository.findAll();
    log.debug("Retrieved turnovers: {}", turnovers);
    return turnovers;
  }
}
