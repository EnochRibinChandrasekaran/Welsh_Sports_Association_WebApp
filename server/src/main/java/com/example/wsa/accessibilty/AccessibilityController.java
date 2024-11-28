package com.example.wsa.accessibilty;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing accessibility enhancements.
 */
@RestController
@RequestMapping("api/accessibility")
@Slf4j
public class AccessibilityController {

  private final AccessibilityService accessibilityService;

  /**
   * Constructs a new AccessibilityController with the specified AccessibilityService.
   *
   * @param accessibilityService the service used to manage accessibility enhancements
   */
  @Autowired
  public AccessibilityController(AccessibilityService accessibilityService) {
    this.accessibilityService = accessibilityService;
  }

  /**
   * Retrieves all accessibility enhancements.
   *
   * @return a list of Accessibility entities
   * @throws AccessibilityException if an error occurs while fetching the data
   */
  @GetMapping
  public List<Accessibility> getAllAccessibility() throws AccessibilityException {
    try {
      log.debug("Entering getAllAccessibility()");
      List<Accessibility> accessibilities = accessibilityService.getAllAccessibilities();
      log.debug("Successfully retrieved accessibility enhancements: {}", accessibilities);
      return accessibilities;
    } catch (Exception e) {
      log.error("Error fetching accessibility data", e);
      throw new AccessibilityException("Error fetching accessibility data", e);
    }
  }
}
