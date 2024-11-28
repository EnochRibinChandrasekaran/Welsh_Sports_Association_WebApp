package com.example.wsa;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the WSA Application.
 */
@SpringBootApplication
@Slf4j
public class WsaApplication {

  /**
   * Main method which starts the Spring Boot application.
   *
   * @param args command-line arguments
   */
  public static void main(String[] args) {
    log.info("Starting WSA Application...");
    SpringApplication.run(WsaApplication.class, args);
    log.info("WSA Application started successfully.");
  }

}
