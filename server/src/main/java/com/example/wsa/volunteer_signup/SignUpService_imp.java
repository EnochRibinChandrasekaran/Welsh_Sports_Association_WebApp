package com.example.wsa.volunteer_signup;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Implementation of the SignUpService interface.
 */
@Service
@Slf4j
public class SignUpService_imp implements SignUpService {

  private final SignUpRepo signUpRepo;

  /**
   * Constructs a new SignUpService_imp with the specified SignUpRepo.
   *
   * @param signUpRepo the repository used for volunteer sign-up operations
   */
  public SignUpService_imp(SignUpRepo signUpRepo) {
    this.signUpRepo = signUpRepo;
  }

  @Override
  public void saveVolunteer(Volunteer volunteer) {
    log.debug("Saving volunteer: {}", volunteer.getUsername());
    signUpRepo.saveVolunteer(volunteer);
    log.debug("Volunteer saved: {}", volunteer.getUsername());
  }

  @Override
  public void addUserEntry(Volunteer volunteer) {
    log.debug("Adding user entry for volunteer: {}", volunteer.getUsername());
    signUpRepo.addUserEntry(volunteer);
    log.debug("User entry added for volunteer: {}", volunteer.getUsername());
  }
}
