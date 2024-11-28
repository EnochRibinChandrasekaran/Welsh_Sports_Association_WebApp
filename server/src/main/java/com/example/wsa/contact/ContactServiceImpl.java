package com.example.wsa.contact;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Implementation of the ContactService interface.
 * This class handles the business logic for contact operations.
 */
@Service
@Slf4j
public class ContactServiceImpl implements ContactService {

  private final ContactRepository contactRepository;

  /**
   * Constructs a new ContactServiceImpl with the specified ContactRepository.
   *
   * @param contactRepository the repository to use
   */
  public ContactServiceImpl(ContactRepository contactRepository) {
    this.contactRepository = contactRepository;
  }

  /**
   * Retrieves a specific contact by its ID.
   *
   * @param id the ID of the contact
   * @return the contact with the specified ID
   */
  @Override
  public Contact getContact(Integer id) {
    log.debug("Getting contact with ID: {}", id);
    Contact contact = contactRepository.getContact(id);
    log.debug("Retrieved contact: {}", contact);
    return contact;
  }

  /**
   * Retrieves a list of all contacts.
   *
   * @return a list of contacts
   */
  @Override
  public List<Contact> getContactList() {
    log.debug("Getting contact list.");
    List<Contact> contacts = contactRepository.getContactList();
    log.debug("Retrieved {} contacts.", contacts.size());
    return contacts;
  }

  /**
   * Saves a contact.
   *
   * @param contact the contact to save
   */
  @Override
  public void saveContact(Contact contact) {
    log.debug("Saving contact: {}", contact);
    contactRepository.saveContactDetails(contact);
    log.debug("Contact saved successfully.");
  }
}
