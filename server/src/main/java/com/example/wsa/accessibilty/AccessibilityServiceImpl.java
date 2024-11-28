package com.example.wsa.accessibilty;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service implementation for managing accessibility enhancements.
 */
@Service
@Slf4j
public class AccessibilityServiceImpl implements AccessibilityService {

  private final AccessibilityRepository accessibilityRepository;

  /**
   * Constructs a new AccessibilityServiceImpl with the specified AccessibilityRepository.
   *
   * @param accessibilityRepository the repository used to access accessibility data
   */
  @Autowired
  public AccessibilityServiceImpl(AccessibilityRepository accessibilityRepository) {
    this.accessibilityRepository = accessibilityRepository;
  }

  /**
   * Retrieves all accessibility enhancements from the repository.
   *
   * @return a list of Accessibility entities
   */
  @Override
  public List<Accessibility> getAllAccessibilities() {
    log.debug("Entering getAllAccessibilities()");
    List<Accessibility> accessibilities = accessibilityRepository.findAll();
    log.debug("Retrieved accessibilities: {}", accessibilities);
    return accessibilities;
  }
}
