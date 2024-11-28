package com.example.wsa.chat;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

/**
 * Controller class for handling chat messages.
 * Manages both public chatroom messages and private messages between users.
 * Reference -
 * <a href="https://hpcodes.medium.com/send-messages-from-spring-boot-backend-to-reactjs-app-using-websocket-4120f6979c9b">...</a>
 * <a href="https://spring.io/guides/gs/messaging-stomp-websocket">...</a>
 */
@Controller
@Slf4j
public class ChatController {

  private final SimpMessagingTemplate simpMessagingTemplate;

  /**
   * Constructs a new ChatController with the specified SimpMessagingTemplate.
   *
   * @param simpMessagingTemplate the messaging template used for sending messages to clients
   */
  @Autowired
  public ChatController(SimpMessagingTemplate simpMessagingTemplate) {
    this.simpMessagingTemplate = simpMessagingTemplate;
  }

  /**
   * Receives a message sent to the "/message"
   * endpoint and broadcasts it to all subscribers of "/chatroom".
   *
   * @param message the message payload
   * @return the message payload to be sent to subscribers
   */
  @MessageMapping("/message")
  @SendTo("/chatroom")
  public Message receiveMessage(@Payload Message message) {
    log.debug("Received public message from {}: {}", message.getSenderName(), message.getMessage());
    return message;
  }

  /**
   * Receives a message sent to the "/private-message" endpoint and sends it to a specific user.
   *
   * @param message the message payload
   * @return the message payload
   */
  @MessageMapping("/private-message")
  public Message recMessage(@Payload Message message) {
    log.debug("Received private message from {} to {}: {}",
            message.getSenderName(), message.getReceiverName(), message.getMessage());
    simpMessagingTemplate.convertAndSendToUser(message.getReceiverName(), "/private", message);
    log.debug("Sent private message to {}: {}", message.getReceiverName(), message.getMessage());
    return message;
  }
}
