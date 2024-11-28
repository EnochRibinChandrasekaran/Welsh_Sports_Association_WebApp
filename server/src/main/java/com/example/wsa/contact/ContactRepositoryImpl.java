package com.example.wsa.contact;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * Implementation of the ContactRepository interface using JdbcTemplate.
 * This class provides database access for contact data.
 */
@Repository
@Slf4j
public class ContactRepositoryImpl implements ContactRepository {

  private final JdbcTemplate jdbcTemplate;
  private RowMapper<Contact> contactRowMapper;

  /**
   * Constructs a new ContactRepositoryImpl with the specified JdbcTemplate.
   *
   * @param jdbcTemplate the JdbcTemplate to use
   */
  public ContactRepositoryImpl(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
    setContactRowMapper();
  }

  /**
   * Sets up the RowMapper for mapping database rows to Contact objects.
   */
  private void setContactRowMapper() {
    contactRowMapper = (rs, i) -> new Contact(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getString("email"),
            rs.getString("subject"),
            rs.getString("message")
    );
  }

  /**
   * Retrieves a specific contact by its ID.
   *
   * @param id the ID of the contact
   * @return the contact with the specified ID
   */
  @Override
  public Contact getContact(Integer id) {
    String sql = "SELECT * FROM ContactInfo WHERE id = ?";
    try {
      log.debug("Retrieving contact with ID: {}", id);
      Contact contact = jdbcTemplate.queryForObject(sql, contactRowMapper, id);
      log.debug("Retrieved contact: {}", contact);
      return contact;
    } catch (Exception e) {
      log.error("Error retrieving contact with ID: {}", id, e);
      throw e;
    }
  }

  /**
   * Retrieves a list of all contacts.
   *
   * @return a list of contacts
   */
  @Override
  public List<Contact> getContactList() {
    String sql = "SELECT * FROM ContactInfo";
    try {
      log.debug("Retrieving all contacts.");
      List<Contact> contacts = jdbcTemplate.query(sql, contactRowMapper);
      log.debug("Retrieved {} contacts.", contacts.size());
      return contacts;
    } catch (Exception e) {
      log.error("Error retrieving contact list.", e);
      throw e;
    }
  }

  /**
   * Saves the details of a contact.
   *
   * @param contact the contact to save
   */
  @Override
  public void saveContactDetails(Contact contact) {
    String sql = "INSERT INTO ContactInfo (name, email, subject, message) VALUES (?, ?, ?, ?)";
    try {
      log.debug("Saving contact: {}", contact);
      jdbcTemplate.update(sql,
              contact.getName(),
              contact.getEmail(),
              contact.getSubject(),
              contact.getMessage()
      );
      log.debug("Contact saved successfully.");
    } catch (Exception e) {
      log.error("Error saving contact: {}", contact, e);
      throw e;
    }
  }
}
