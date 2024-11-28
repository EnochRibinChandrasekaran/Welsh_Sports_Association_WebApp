package com.example.wsa.login;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Entity representing a user in the system.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "password")
public class User {

  /**
   * Unique identifier for the user.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * Username for login.
   */
  private String username;

  /**
   * Password for login.
   */
  private String password;
}
