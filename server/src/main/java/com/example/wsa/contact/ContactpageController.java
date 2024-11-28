package com.example.wsa.contact;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for handling contact form submissions.
 * This controller receives contact form data from the client,
 * saves it, and sends a notification email.
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/home/contactus")
@Slf4j
public class ContactpageController {

  private final ContactService contactService;
  private final EmailService emailService;

  /**
   * Constructs a new ContactpageController with the specified services.
   *
   * @param contactService the contact service
   * @param emailService   the email service
   */
  @Autowired
  public ContactpageController(ContactService contactService, EmailService emailService) {
    this.contactService = contactService;
    this.emailService = emailService;
  }

  /**
   * Processes the contact form submission.
   *
   * @param contact the contact form data
   * @return a ResponseEntity indicating the result of the operation
   */
  @PostMapping("/addContact")
  public ResponseEntity<String> processContactForm(@RequestBody Contact contact) {
    try {
      log.debug("Received contact form submission: {}", contact);
      contactService.saveContact(contact);
      log.debug("Contact saved successfully: {}", contact.getId());
      emailService.sendEmail(contact);
      log.debug("Email sent successfully for contact ID: {}", contact.getId());
      return ResponseEntity.ok("Contact added and email sent successfully.");
    } catch (Exception e) {
      log.error("Failed to add contact or send email for contact: {}", contact, e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
              .body("Failed to add contact or send email.");
    }
  }
}
