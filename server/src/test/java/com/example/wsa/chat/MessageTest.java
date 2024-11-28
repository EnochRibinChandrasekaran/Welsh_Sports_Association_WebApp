package com.example.wsa.chat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the {@link Message} class.
 */
public class MessageTest {

  /**
   * Tests the no-argument constructor of the {@link Message} class.
   */
  @Test
  public void testNoArgsConstructor() {
    Message message = new Message();
    assertNotNull(message);
  }

  /**
   * Tests the all-argument constructor of the {@link Message} class.
   */
  @Test
  public void testAllArgsConstructor() {
    String senderName = "Enoch";
    String receiverName = "Rob";
    String messageContent = "Hi Rob!";
    String date = "2023-09-19";
    Status status = Status.MESSAGE;

    Message message = new Message(senderName, receiverName, messageContent, date, status);

    assertEquals(senderName, message.getSenderName());
    assertEquals(receiverName, message.getReceiverName());
    assertEquals(messageContent, message.getMessage());
    assertEquals(date, message.getDate());
    assertEquals(status, message.getStatus());
  }

  /**
   * Tests the getters and setters of the {@link Message} class.
   */
  @Test
  public void testGettersAndSetters() {
    Message message = new Message();
    String senderName = "Enoch";
    String receiverName = "Rob";
    String messageContent = "Hi Rob!";
    String date = "2023-09-19";
    Status status = Status.MESSAGE;

    message.setSenderName(senderName);
    message.setReceiverName(receiverName);
    message.setMessage(messageContent);
    message.setDate(date);
    message.setStatus(status);

    assertEquals(senderName, message.getSenderName());
    assertEquals(receiverName, message.getReceiverName());
    assertEquals(messageContent, message.getMessage());
    assertEquals(date, message.getDate());
    assertEquals(status, message.getStatus());
  }

  /**
   * Tests the {@code toString} method of the {@link Message} class.
   */
  @Test
  public void testToString() {
    String senderName = "Enoch";
    String receiverName = "Rob";
    String messageContent = "Hi Rob!";
    String date = "2023-09-19";
    Status status = Status.MESSAGE;
    Message message = new Message(senderName, receiverName, messageContent, date, status);
    String expectedString = "Message(senderName=Enoch, receiverName=Rob, "
            + "message=Hi Rob!, date=2023-09-19, status=MESSAGE)";
    assertEquals(expectedString, message.toString());
  }
}
