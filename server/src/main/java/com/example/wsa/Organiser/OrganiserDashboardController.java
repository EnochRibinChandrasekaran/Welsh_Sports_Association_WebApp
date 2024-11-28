package com.example.wsa.organiser;

import com.example.wsa.event.EventDTO;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for handling organiser dashboard-related requests.
 * Provides endpoints for fetching upcoming and past events for a specific organiser
 * and serving event images from the server.
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/organiser-dashboard")
@Slf4j
public class OrganiserDashboardController {

  private final OrganiserDashboardRepo organiserDashboardRepo;

  /**
   * Constructs an OrganiserDashboardController with the given OrganiserDashboardRepo.
   *
   * @param organiserDashboardRepo the repository for organiser dashboard operations
   */
  @Autowired
  public OrganiserDashboardController(OrganiserDashboardRepo organiserDashboardRepo) {
    this.organiserDashboardRepo = organiserDashboardRepo;
  }

  /**
   * Retrieves a list of upcoming events for the organiser.
   *
   * @param id the ID of the organiser
   * @return a list of EventDTO objects representing the upcoming events
   */
  @GetMapping("/events/upcoming/{id}")
  public List<EventDTO> getUpcomingEvents(@PathVariable int id) {
    log.debug("Entering getUpcomingEvents() with organiser id: {}", id);
    List<EventDTO> events = organiserDashboardRepo.getUpcomingEvents(id);
    log.debug("Retrieved {} upcoming events for organiser id: {}", events.size(), id);
    return events;
  }

  /**
   * Retrieves a list of past events for the organiser.
   *
   * @param id the ID of the organiser
   * @return a list of EventDTO objects representing the past events
   */
  @GetMapping("/events/past/{id}")
  public List<EventDTO> getPastEvents(@PathVariable int id) {
    log.debug("Entering getPastEvents() with organiser id: {}", id);
    List<EventDTO> events = organiserDashboardRepo.getPastEvents(id);
    log.debug("Retrieved {} past events for organiser id: {}", events.size(), id);
    return events;
  }

  /**
   * Serves event images from the server's file system.
   *
   * @param filename the name of the image file to retrieve
   * @return a ResponseEntity containing the image resource
   */
  @GetMapping("/event-image/{filename:.+}")
  public ResponseEntity<Resource> getEventImage(@PathVariable String filename) {
    log.debug("Entering getEventImage() with filename: {}", filename);
    try {
      Path filePath = Paths.get("src/main/resources/static/uploads").resolve(filename).normalize();
      Resource resource = new UrlResource(filePath.toUri());

      if (resource.exists() && resource.isReadable()) {
        log.debug("Serving event image: {}", filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "inline; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
      } else {
        log.warn("Event image not found or not readable: {}", filename);
        return ResponseEntity.notFound().build();
      }
    } catch (Exception e) {
      log.error("Error in getEventImage() for filename: {}", filename, e);
      return ResponseEntity.internalServerError().build();
    }
  }
}
