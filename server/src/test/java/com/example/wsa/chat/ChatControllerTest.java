package com.example.wsa.chat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.messaging.simp.SimpMessagingTemplate;

/**
 * Unit tests for the {@link ChatController} class.
 */
public class ChatControllerTest {

  @Mock
  private SimpMessagingTemplate simpMessagingTemplate;

  @InjectMocks
  private ChatController chatController;

  /**
   * Initializes mocks before each test method.
   */
  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
  }

  /**
   * Tests the {@code receiveMessage} method for a successful case.
   */
  @Test
  public void testReceiveMessage_Pass() {
    Message message = new Message("Enoch", "Ribin", "Hi Rob", "2023-09-19", Status.MESSAGE);
    Message result = chatController.receiveMessage(message);
    assertEquals(message, result);
  }

  /**
   * Tests the {@code receiveMessage} method for a failure case.
   */
  @Test
  public void testReceiveMessage_Fail() {
    Message originalMessage = new Message("Enoch", "Ribin", "Hi Rob", "2023-09-19", Status.MESSAGE);
    Message wrongMessage = new Message("Rob", "Rob", "Wrong Message", "2023-09-19", Status.MESSAGE);
    Message result = chatController.receiveMessage(originalMessage);
    assertNotEquals(wrongMessage, result);
  }

  /**
   * Tests the {@code recMessage} method for a successful case.
   */
  @Test
  public void testRecMessage_Pass() {
    Message message = new Message("Enoch", "Ribin", "Hi Rob", "2023-09-19", Status.MESSAGE);
    Message result = chatController.recMessage(message);
    assertEquals(message, result);
    verify(simpMessagingTemplate).convertAndSendToUser("Ribin", "/private", message);
  }

  /**
   * Tests the {@code recMessage} method for a failure case.
   */
  @Test
  public void testRecMessage_Fail() {
    Message message = new Message("Enoch", "Ribin", "Hi Rob", "2023-09-19", Status.MESSAGE);
    Message result = chatController.recMessage(message);
    assertEquals(message, result);
    verify(simpMessagingTemplate, never()).convertAndSendToUser("Rob", "/private", message);
  }
}
