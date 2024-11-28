package com.example.wsa.qualification;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing qualifications.
 */
@RestController
@RequestMapping("api/qualifications")
@Slf4j
public class QualificationController {

  private final QualificationService qualificationService;

  /**
   * Constructs a new QualificationController with the specified QualificationService.
   *
   * @param qualificationService the service used to manage qualifications
   */
  @Autowired
  public QualificationController(QualificationService qualificationService) {
    this.qualificationService = qualificationService;
  }

  /**
   * Retrieves all qualifications.
   *
   * @return a list of all qualifications
   * @throws QualificationException if an error occurs while fetching qualifications
   */
  @GetMapping
  public List<Qualification> getQualifications() throws QualificationException {
    try {
      log.debug("Entering getQualifications()");
      List<Qualification> qualifications = qualificationService.getAllQualifications();
      log.debug("Retrieved {} qualifications", qualifications.size());
      return qualifications;
    } catch (Exception e) {
      log.error("Error fetching qualifications", e);
      throw new QualificationException("Error fetching Qualifications", e);
    }
  }
}
