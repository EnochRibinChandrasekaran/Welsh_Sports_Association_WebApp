package com.example.wsa.accessibilty;

import java.util.List;

/**
 * Service interface for managing accessibility enhancements.
 */
public interface AccessibilityService {

  /**
   * Retrieves all accessibility enhancements.
   *
   * @return a list of Accessibility entities
   */
  List<Accessibility> getAllAccessibilities();
}
