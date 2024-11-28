package com.example.wsa.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * Configuration class for Cross-Origin Resource Sharing (CORS) settings.
 * This configuration allows specified origins to make cross-origin requests to the application.
 */
@Configuration
@Slf4j
public class CorsConfig {

  /**
   * Creates a CorsFilter bean with specified configurations.
   *
   * @return a CorsFilter that handles CORS requests
   */
  @Bean
  public CorsFilter corsFilter() {
    log.debug("Initializing CorsFilter with custom CORS configurations");

    CorsConfiguration config = new CorsConfiguration();
    config.setAllowCredentials(true);
    config.addAllowedOrigin("http://localhost:3000");
    config.addAllowedOrigin("http://localhost:3001");
    config.addAllowedOrigin("http://localhost:3002");
    config.addAllowedOrigin("http://localhost:3003");
    config.addAllowedOrigin("http://localhost:3004");
    config.addAllowedOrigin("http://localhost:3005");
    config.addAllowedOrigin("http://localhost:3006");
    config.addAllowedOrigin("http://localhost:3007");
    config.addAllowedOrigin("http://localhost:3008");
    config.addAllowedOrigin("http://localhost:3009");
    config.addAllowedOrigin("http://localhost:3010");
    config.addAllowedHeader("*");
    config.addAllowedMethod("*");

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", config);

    log.debug("CorsFilter initialized with allowed origins: {}", config.getAllowedOrigins());

    return new CorsFilter(source);
  }
}
