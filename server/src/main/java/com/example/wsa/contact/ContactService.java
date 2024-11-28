package com.example.wsa.contact;

import java.util.List;

/**
 * Service interface for managing contact-related operations.
 * This interface defines methods for handling business logic related to contacts.
 */
public interface ContactService {

  /**
   * Retrieves a list of all contacts.
   *
   * @return a list of contacts
   */
  List<Contact> getContactList();

  /**
   * Retrieves a specific contact by its ID.
   *
   * @param id the ID of the contact
   * @return the contact with the specified ID
   */
  Contact getContact(Integer id);

  /**
   * Saves a contact.
   *
   * @param contact the contact to save
   */
  void saveContact(Contact contact);
}
