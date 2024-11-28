package com.example.wsa.chat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Represents a chat message exchanged between users.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Message {

  /**
   * The name of the sender.
   */
  private String senderName;

  /**
   * The name of the receiver (used for private messages).
   */
  private String receiverName;

  /**
   * The content of the message.
   */
  private String message;

  /**
   * The date and time when the message was sent.
   */
  private String date;

  /**
   * The status of the message (JOIN, MESSAGE, LEAVE).
   */
  private Status status;
}
