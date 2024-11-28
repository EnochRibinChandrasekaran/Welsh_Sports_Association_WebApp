package com.example.wsa.login;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for handling user-related operations.
 */
@RestController
@RequestMapping("/api")
@Slf4j
public class UserController {

  private final UserService userService;

  /**
   * Constructs a new UserController with the specified UserService.
   *
   * @param userService the service used for user-related operations
   */
  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  /**
   * Retrieves all users.
   *
   * @return a list of all users
   */
  @GetMapping("/users")
  public List<User> getAllUsers() {
    log.debug("Fetching all users");
    List<User> users = userService.findAll();
    log.debug("Retrieved {} users", users.size());
    return users;
  }
}
