package com.example.wsa.turnover;

import java.util.List;

/**
 * Service interface for Turnover-related operations.
 */
public interface TurnoverService {

  /**
   * Retrieves all turnover records.
   *
   * @return a list of all turnovers
   */
  List<Turnover> getAllTurnovers();
}
