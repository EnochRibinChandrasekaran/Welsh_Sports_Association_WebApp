package com.example.wsa.contact;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a contact message submitted by a user.
 * This class holds the data for a contact form submission, including the user's name, email,
 * subject, and message content.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contact {

  /**
   * Unique identifier for the contact message.
   */
  private Integer id;

  /**
   * Name of the person who submitted the contact message.
   */
  private String name;

  /**
   * Email address of the person who submitted the contact message.
   */
  private String email;

  /**
   * Subject of the contact message.
   */
  private String subject;

  /**
   * Content of the contact message.
   */
  private String message;
}
