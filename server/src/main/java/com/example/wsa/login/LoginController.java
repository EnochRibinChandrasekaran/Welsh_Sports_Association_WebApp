package com.example.wsa.login;

import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for handling user login operations.
 */
@RestController
@RequestMapping("/api")
@Slf4j
public class LoginController {

  private final UserService userService;

  /**
   * Constructs a new LoginController with the specified UserService.
   *
   * @param userService the service used for user-related operations
   */
  public LoginController(UserService userService) {
    this.userService = userService;
  }

  /**
   * Authenticates a user based on the provided credentials.
   *
   * @param loginUser the user credentials from the request body
   * @return a ResponseEntity containing a success message
   *      and userId if authentication is successful,
   *      or an error message if authentication fails.
   */
  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody User loginUser) {
    log.debug("Attempting to authenticate user: {}", loginUser.getUsername());
    try {
      User user = userService.findByUsername(loginUser.getUsername());
      if (user != null && loginUser.getPassword().equals(user.getPassword())) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Login successful");
        response.put("userId", user.getId());
        log.info("User '{}' logged in successfully.", user.getUsername());
        return ResponseEntity.ok(response);
      } else {
        log.warn("Invalid login attempt for username: {}", loginUser.getUsername());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
      }
    } catch (Exception e) {
      log.error("An error occurred during login for user: {}", loginUser.getUsername(), e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
              .body("An error occurred during login.");
    }
  }
}
