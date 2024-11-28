package com.example.wsa.volunteer_signup;

/**
 * Service interface for volunteer sign-up operations.
 */
public interface SignUpService {

  /**
   * Saves a volunteer to the database.
   *
   * @param volunteer the volunteer to save
   */
  void saveVolunteer(Volunteer volunteer);

  /**
   * Adds a user entry for authentication purposes.
   *
   * @param volunteer the volunteer whose user entry is to be added
   */
  void addUserEntry(Volunteer volunteer);
}
