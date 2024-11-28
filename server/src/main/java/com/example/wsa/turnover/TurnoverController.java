package com.example.wsa.turnover;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing Turnover entities.
 */
@RestController
@RequestMapping("api/turnover")
@Slf4j
public class TurnoverController {

  private final TurnoverService turnoverService;

  /**
   * Constructs a new TurnoverController with the given TurnoverService.
   *
   * @param turnoverService the service used to manage turnovers
   */
  @Autowired
  public TurnoverController(TurnoverService turnoverService) {
    this.turnoverService = turnoverService;
  }

  /**
   * Retrieves all turnover records.
   *
   * @return a list of all turnovers
   * @throws TurnoverException if an error occurs during retrieval
   */
  @GetMapping
  public List<Turnover> getAllTurnovers() throws TurnoverException {
    try {
      log.debug("Entering TurnoverController.getAllTurnovers()");
      List<Turnover> turnovers = turnoverService.getAllTurnovers();
      log.debug("Successfully retrieved turnovers: {}", turnovers);
      return turnovers;
    } catch (Exception e) {
      log.error("Error retrieving turnover data", e);
      throw new TurnoverException("Error retrieving turnover data", e);
    }
  }
}
