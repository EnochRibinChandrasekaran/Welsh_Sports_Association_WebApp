package com.example.wsa.contact;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 * Service for sending emails.
 * This class handles the creation and sending of emails based on contact information.
 */
@Service
@Slf4j
public class EmailService {

  private final JavaMailSender javaMailSender;

  /**
   * Constructs a new EmailService with the specified JavaMailSender.
   *
   * @param javaMailSender the JavaMailSender to use
   */
  public EmailService(JavaMailSender javaMailSender) {
    this.javaMailSender = javaMailSender;
  }

  /**
   * Sends an email based on the contact information.
   *
   * @param contact the contact information
   * @throws MessagingException if an error occurs while sending the email
   */
  public void sendEmail(Contact contact) throws MessagingException {
    log.debug("Preparing to send email for contact: {}", contact);
    try {
      MimeMessage message = javaMailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(message, true);
      helper.setTo("isocmatchtest10@gmail.com"); // Replace with your specified email
      helper.setSubject("New Contact Form Submission");
      String emailContent = "Name: " + contact.getName() + "\n"
              + "Email: " + contact.getEmail() + "\n"
              + "Subject: " + contact.getSubject() + "\n"
              + "Message: " + contact.getMessage();
      helper.setText(emailContent);
      javaMailSender.send(message);
      log.debug("Email sent successfully for contact ID: {}", contact.getId());
    } catch (MessagingException e) {
      log.error("Failed to send email for contact: {}", contact, e);
      throw e;
    }
  }
}
