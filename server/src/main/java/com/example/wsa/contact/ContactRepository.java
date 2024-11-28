package com.example.wsa.contact;

import java.util.List;

/**
 * Repository interface for managing contact data.
 * This interface defines methods for retrieving and saving contact information.
 */
public interface ContactRepository {

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
   * Saves the details of a contact.
   *
   * @param contact the contact to save
   */
  void saveContactDetails(Contact contact);
}
