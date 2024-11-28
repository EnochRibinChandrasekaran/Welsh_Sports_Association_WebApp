package com.example.wsa.volunteer_signup;

import java.time.LocalDate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * Implementation of the SignUpRepo interface for accessing the database.
 */
@Repository
@Slf4j
public class SignUpRepo_imp implements SignUpRepo {

  private final JdbcTemplate jdbc;
  private RowMapper<Volunteer> volunteerItemMapper;

  /**
   * Constructs a new SignUpRepo_imp with the specified JdbcTemplate.
   *
   * @param jdbcTemplate the JdbcTemplate used for database operations
   */
  public SignUpRepo_imp(JdbcTemplate jdbcTemplate) {
    this.jdbc = jdbcTemplate;
    setVolunteerItemMapper();
  }

  /**
   * Sets the RowMapper for Volunteer objects.
   */
  private void setVolunteerItemMapper() {
    volunteerItemMapper = (rs, i) -> new Volunteer(
            rs.getLong("id"),
            rs.getString("username"),
            rs.getString("password"),
            rs.getString("first_name"),
            rs.getString("last_name"),
            rs.getString("email"),
            rs.getString("phone_number"),
            rs.getObject("dob", LocalDate.class),
            rs.getString("address"),
            rs.getString("postal_code"),
            rs.getString("occupation"),
            rs.getString("qualifications"),
            rs.getString("availability"),
            rs.getString("roles"),
            rs.getString("dbs"),
            rs.getString("accessibility_enhancement"),
            rs.getString("about"),
            rs.getInt("rewards_earned"),
            rs.getString("emergency_contact_name"),
            rs.getString("emergency_phone_number"),
            rs.getString("emergency_relationship"),
            rs.getString("goals"),
            rs.getString("interests"),
            rs.getString("references"),
            rs.getBoolean("agree_to_policies"),
            rs.getString("member_status"),
            rs.getFloat("rating"),
            rs.getString("membership_level"),
            rs.getInt("event_attended"),
            rs.getString("gender"),
            rs.getBytes("image")
    );
  }

  @Override
  public void saveVolunteer(Volunteer volunteer) {
    String sql = "INSERT INTO volunteer (username, password, first_name, last_name, email, "
            + "phone_number, dob, address, postal_code, occupation, "
            + "qualifications, availability, roles, dbs, accessibility_enhancement, "
            + "about, rewards_earned, emergency_contact_name, emergency_phone_number, "
            + "emergency_relationship, goals, interests, `references`, agree_to_policies, "
            + "member_status, rating, membership_level, event_attended, gender, image) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
            + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    try {
      jdbc.update(sql,
              volunteer.getUsername(),
              volunteer.getPassword(),
              volunteer.getFirstName(),
              volunteer.getLastName(),
              volunteer.getEmail(),
              volunteer.getPhoneNumber(),
              volunteer.getDob(),
              volunteer.getAddress(),
              volunteer.getPostalCode(),
              volunteer.getOccupation(),
              volunteer.getQualifications(),
              volunteer.getAvailability(),
              volunteer.getRoles(),
              volunteer.getDbs(),
              volunteer.getAccessibilityEnhancement(),
              volunteer.getAbout(),
              volunteer.getRewardsEarned(),
              volunteer.getEmergencyContactName(),
              volunteer.getEmergencyPhoneNumber(),
              volunteer.getEmergencyRelationship(),
              volunteer.getGoals(),
              volunteer.getInterests(),
              volunteer.getReferences(),
              volunteer.getAgreeToPolicies(),
              volunteer.getMemberStatus(),
              volunteer.getRating(),
              volunteer.getMembershipLevel(),
              volunteer.getEventAttended(),
              volunteer.getGender(),
              volunteer.getImage()
      );
      log.debug("Volunteer saved successfully: {}", volunteer.getUsername());
    } catch (Exception e) {
      log.error("Error saving volunteer: {}", volunteer.getUsername(), e);
      throw e;
    }
  }

  @Override
  public void addUserEntry(Volunteer volunteer) {
    String username = volunteer.getUsername();
    String password = volunteer.getPassword();
    String email = volunteer.getEmail();

    String sql = "INSERT INTO user (username, password, email) VALUES (?, ?, ?)";

    try {
      jdbc.update(sql, username, password, email);
      log.debug("User entry added successfully for username: {}", username);
    } catch (Exception e) {
      log.error("Error adding user entry for username: {}", username, e);
      throw e;
    }
  }

  /**
   * Retrieves a volunteer by their ID.
   *
   * @param id the ID of the volunteer
   * @return the Volunteer object
   */
  public Volunteer getVolunteerById(Long id) {
    String sql = "SELECT * FROM volunteer WHERE id = ?";
    try {
      Volunteer volunteer = jdbc.queryForObject(sql, volunteerItemMapper, id);
      log.debug("Retrieved volunteer with ID {}: {}", id, volunteer);
      return volunteer;
    } catch (Exception e) {
      log.error("Error retrieving volunteer with ID {}", id, e);
      throw e;
    }
  }
}
