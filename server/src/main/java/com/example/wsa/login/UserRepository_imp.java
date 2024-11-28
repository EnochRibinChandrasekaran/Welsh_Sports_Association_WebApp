package com.example.wsa.login;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * Implementation of the UserRepository interface using JdbcTemplate.
 */
@Repository
@Slf4j
public class UserRepository_imp implements UserRepository {

  private final JdbcTemplate jdbc;
  private final RowMapper<User> userItemMapper;

  /**
   * Constructs a new UserRepository_imp with the specified JdbcTemplate.
   *
   * @param jdbc the JdbcTemplate used for database operations
   */
  public UserRepository_imp(JdbcTemplate jdbc) {
    this.jdbc = jdbc;
    this.userItemMapper = (rs, i) -> new User(
            rs.getLong("id"),
            rs.getString("username"),
            rs.getString("password")
    );
  }

  @Override
  public List<User> findAll() {
    String sql = "SELECT * FROM user";
    log.debug("Executing SQL query: {}", sql);
    List<User> users = jdbc.query(sql, userItemMapper);
    log.debug("Retrieved {} users from the database", users.size());
    return users;
  }

  @Override
  public User findByUsername(String username) {
    String sql = "SELECT * FROM user WHERE username = ?";
    log.debug("Executing SQL query: {} with username: {}", sql, username);
    try {
      User user = jdbc.queryForObject(sql, userItemMapper, username);
      log.debug("User found: {}", user);
      return user;
    } catch (EmptyResultDataAccessException e) {
      log.warn("No user found with username: {}", username);
      return null;
    } catch (Exception e) {
      log.error("Error occurred while finding user by username: {}", username, e);
      throw e;
    }
  }
}
