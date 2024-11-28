package com.example.wsa.login;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Implementation of the UserService interface.
 */
@Service
@Slf4j
public class UserService_imp implements UserService {

  private final UserRepository userRepository;

  /**
   * Constructs a new UserService_imp with the specified UserRepository.
   *
   * @param userRepository the repository used for user-related operations
   */
  public UserService_imp(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  /**
   * Retrieves all users from the repository.
   *
   * @return a list of all users
   */
  @Override
  public List<User> findAll() {
    log.debug("Entering findAll()");
    List<User> users = userRepository.findAll();
    log.debug("Retrieved {} users", users.size());
    return users;
  }

  /**
   * Finds a user by their username.
   *
   * @param username the username to search for
   * @return the User object if found, null otherwise
   */
  @Override
  public User findByUsername(String username) {
    log.debug("Entering findByUsername() with username: {}", username);
    User user = userRepository.findByUsername(username);
    if (user != null) {
      log.debug("User '{}' found.", user.getUsername());
    } else {
      log.warn("User with username '{}' not found.", username);
    }
    return user;
  }
}
