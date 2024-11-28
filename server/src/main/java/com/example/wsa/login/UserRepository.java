package com.example.wsa.login;

import java.util.List;

/**
 * Repository interface for user-related database operations.
 */
public interface UserRepository {

  /**
   * Retrieves all users from the database.
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
