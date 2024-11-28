package com.example.wsa.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * Configuration class for setting up WebSocket message broker.
 * Enables STOMP protocol for messaging between client and server over WebSocket.
 *
 * <p>
 * References:
 * <ul>
 *   <li><a href="https://hpcodes.medium.com/send-messages-from-spring-boot-backend-to-reactjs-app-using-websocket-4120f6979c9b">
 *       Send messages from Spring Boot backend to ReactJS app using WebSocket</a></li>
 *   <li><a href="https://spring.io/guides/gs/messaging-stomp-websocket">
 *       Spring Guide on Messaging with STOMP over WebSocket</a></li>
 * </ul>
 * </p>
 */
@Configuration
@EnableWebSocketMessageBroker
@Slf4j
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

  /**
   * Registers STOMP endpoints mapping each to a specific URL and enabling SockJS fallback options.
   *
   * @param registry the registry to add STOMP endpoints to
   */
  @Override
  public void registerStompEndpoints(StompEndpointRegistry registry) {
    log.debug("Registering STOMP endpoint at '/ws'");
    registry.addEndpoint("/ws").setAllowedOriginPatterns("*").withSockJS();
  }

  /**
   * Configures message broker options like
   * application destination prefixes and enables simple broker.
   *
   * @param registry the registry to configure message broker options
   */
  @Override
  public void configureMessageBroker(MessageBrokerRegistry registry) {
    log.debug("Configuring message broker with application destination "
            + "prefix '/app' and enabling simple broker for '/chatroom' and '/user'");
    registry.setApplicationDestinationPrefixes("/app");
    registry.enableSimpleBroker("/chatroom", "/user");
    registry.setUserDestinationPrefix("/user");
  }
}
