package com.example.wsa.quickRegister;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for handling quick volunteer registration.
 */
@RestController
@RequestMapping("/api/quickregister")
@Slf4j
public class QuickRegisterController {

  private final QuickRegisterService quickRegisterService;

  /**
   * Constructs a new QuickRegisterController with the specified QuickRegisterService.
   *
   * @param quickRegisterService the service used for quick registration
   */
  @Autowired
  public QuickRegisterController(QuickRegisterService quickRegisterService) {
    this.quickRegisterService = quickRegisterService;
  }

  /**
   * Registers a volunteer quickly for an event.
   *
   * @param quickRegisterDTO the data transfer object containing volunteer information
   * @return a ResponseEntity with a success message
   */
  @PostMapping
  public ResponseEntity<String> quickRegisterVolunteer(
          @RequestBody QuickRegisterDTO quickRegisterDTO) {
    log.debug("Received quick registration request: {}", quickRegisterDTO);
    ResponseEntity<String> response = quickRegisterService.quickRegisterVolunteer(quickRegisterDTO);
    log.debug("Quick registration response: {}", response);
    return response;
  }
}
