package com.example.wsa.qualification;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service implementation for managing qualifications.
 */
@Service
@Slf4j
public class QualificationServiceImpl implements QualificationService {

  private final QualificationRespository qualificationRepository;

  /**
   * Constructs a new QualificationServiceImpl with the specified QualificationRepository.
   *
   * @param qualificationRepository the repository used to access qualification data
   */
  @Autowired
  public QualificationServiceImpl(QualificationRespository qualificationRepository) {
    this.qualificationRepository = qualificationRepository;
  }

  /**
   * Retrieves all qualifications from the database.
   *
   * @return a list of all qualifications
   */
  @Override
  public List<Qualification> getAllQualifications() {
    log.debug("Entering getAllQualifications()");
    List<Qualification> qualifications = qualificationRepository.findAll();
    log.debug("Retrieved {} qualifications", qualifications.size());
    return qualifications;
  }
}
