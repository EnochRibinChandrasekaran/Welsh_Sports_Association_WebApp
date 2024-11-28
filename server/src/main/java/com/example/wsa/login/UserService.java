package com.example.wsa.login;

import java.util.List;

/**
 * Service interface for user-related operations.
 */
public interface UserService {

  /**
   * Retrieves all users.
   *
   * @return a list of all users
   */
  List<User> findAll();

  /**
   * Finds a user by their username.
   *
   * @param username the username to search
   * @return the User if found, null otherwise
   */
  User findByUsername(String username);
}
