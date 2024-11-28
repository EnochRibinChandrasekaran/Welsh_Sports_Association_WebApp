package com.example.wsa.volunteer;

import com.example.wsa.event.EventDTO;
import com.example.wsa.utility.NotNullResponse;
import com.example.wsa.volunteer.exception.VolunteerControllerException;
import com.example.wsa.volunteer.exception.VolunteerException;
import com.example.wsa.volunteer.exception.VolunteerServiceException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class that handles HTTP requests related to Volunteers.
 * Provides endpoints for fetching, updating, and managing volunteer information.
 */
@Slf4j
@RestController("volunteerControllerV1")
@RequestMapping("api/volunteer")
public class VolunteerController {

  private final VolunteerService volunteerService;

  /**
   * Constructs a new VolunteerController with the specified VolunteerService.
   *
   * @param volunteerService the service used to manage volunteers
   */
  @Autowired
  public VolunteerController(VolunteerService volunteerService) {
    this.volunteerService = volunteerService;
  }

  /**
   * Retrieves a list of upcoming approved events.
   *
   * @return a list of NotNullResponse wrapping EventDTO objects representing upcoming events.
   */
  @GetMapping("/upcoming-events")
  public List<NotNullResponse<EventDTO>> upcomingEvents() {
    log.debug("Entering VolunteerController.upcomingEvents()");
    return volunteerService.upcomingEvents()
            .stream().map(NotNullResponse::new).collect(Collectors.toList());
  }

  /**
   * Retrieves a list of approved events dates for the event calendar.
   *
   * @return a list of NotNullResponse wrapping EventDTO objects with approved event dates.
   * @throws VolunteerException if an error occurs while fetching the events.
   */
  @GetMapping("/event-calendar")
  public List<NotNullResponse<EventDTO>> approvedEventsDates() throws VolunteerException {
    log.debug("Entering VolunteerController.approvedEventsDates()");
    try {
      return volunteerService.approvedEventsDates()
              .stream().map(NotNullResponse::new).collect(Collectors.toList());
    } catch (VolunteerServiceException e) {
      log.error("VolunteerController.approvedEventsDates() - Exception: ", e);
      throw new VolunteerControllerException(
              "Volunteer Service Exception in approvedEventsDates()", e);
    }
  }

  /**
   * Retrieves volunteer details using the volunteer's ID.
   *
   * @param id the ID of the volunteer.
   * @return a NotNullResponse wrapping VolunteerDTO containing volunteer details.
   * @throws VolunteerException if the volunteer is not found or an error occurs.
   */
  @GetMapping("/{id}")
  public NotNullResponse<VolunteerDTO> getVolunteer(@PathVariable int id)
          throws VolunteerException {
    log.debug("Entering VolunteerController.getVolunteer({})", id);
    try {
      return new NotNullResponse<>(volunteerService.getVolunteerDetails(id));
    } catch (VolunteerServiceException e) {
      log.error("VolunteerController.getVolunteer({}) - Exception: ", id, e);
      throw new VolunteerControllerException("Volunteer service exception in getVolunteer()", e);
    }
  }

  /**
   * Updates volunteer information.
   *
   * @param id        the ID of the volunteer to update.
   * @param volunteer the VolunteerDTO containing updated volunteer information.
   * @return ResponseEntity containing the updated VolunteerDTO.
   * @throws VolunteerException if an error occurs during the update.
   */
  @PutMapping("/{id}")
  public ResponseEntity<VolunteerDTO> updateVolunteerInfo(
          @PathVariable int id,
          @RequestBody VolunteerDTO volunteer)
          throws VolunteerException {
    log.debug("Entering VolunteerController.updateVolunteerInfo({}): Volunteer: {}", id, volunteer);
    try {
      VolunteerDTO updatedVolunteer = volunteerService.updateVolunteerInfo(id, volunteer);
      log.debug("VolunteerController.updateVolunteerInfo({}) - Success: {}", id, updatedVolunteer);
      return ResponseEntity.ok(updatedVolunteer);
    } catch (VolunteerServiceException e) {
      log.error("VolunteerController.updateVolunteerInfo({}) - Exception: ", id, e);
      throw new VolunteerControllerException(
              "Volunteer service exception in updateVolunteerInfo()", e);
    }
  }

  /**
   * Uploads a profile picture for the volunteer.
   *
   * @param id      the ID of the volunteer.
   * @param payload a map containing the base64 encoded profile image under the key "profileImage".
   * @return ResponseEntity containing a success message.
   * @throws VolunteerException if an error occurs while uploading the image.
   */
  @PostMapping("/profile-picture/{id}")
  public ResponseEntity<String> uploadProfilePicture(
          @PathVariable int id,
          @RequestBody Map<String, String> payload)
          throws VolunteerException {
    log.debug("Entering VolunteerController.uploadProfilePicture({})", id);
    try {
      String base64Image = payload.get("profileImage");
      String imageData = base64Image.split(",")[1];
      volunteerService.saveImage(imageData, id);
      log.debug("VolunteerController.uploadProfilePicture({}) - Image uploaded successfully", id);
      return ResponseEntity.ok("Image uploaded");
    } catch (VolunteerServiceException e) {
      log.error("VolunteerController.uploadProfilePicture({}) - Exception: ", id, e);
      throw new VolunteerControllerException(
              "Volunteer service exception in saveImage(imageData, id)", e);
    }
  }

  /**
   * Saves a new volunteer with basic information.
   *
   * @param payload a map containing volunteer information:
   *                "firstName", "lastName", "email", "phoneNumber".
   * @return the ID of the saved volunteer.
   */
  @PostMapping("/saveVolunteer")
  public Integer saveVolunteer(@RequestBody Map<String, String> payload) {
    log.debug("Entering VolunteerController.saveVolunteer()");
    String firstName = payload.get("firstName");
    String lastName = payload.get("lastName");
    String email = payload.get("email");
    String phoneNumber = payload.get("phoneNumber");
    Integer volunteerId = volunteerService.saveVolunteer(firstName, lastName, email, phoneNumber);
    log.debug("VolunteerController.saveVolunteer() - Volunteer saved with ID: {}", volunteerId);
    return volunteerId;
  }

  /**
   * Retrieves the profile picture of a volunteer.
   *
   * @param id the ID of the volunteer.
   * @return ResponseEntity containing the image bytes and appropriate headers.
   * @throws VolunteerException if an error occurs while retrieving the image.
   */
  @GetMapping("/profile-picture/{id}")
  public ResponseEntity<byte[]> getProfilePicture(@PathVariable int id) throws VolunteerException {
    log.debug("Entering VolunteerController.getProfilePicture({})", id);
    try {
      byte[] image = volunteerService.getProfilePicture(id);
      if (image == null) {
        log.warn("VolunteerController.getProfilePicture({}) - Image not found", id);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
      String contentType = URLConnection
              .guessContentTypeFromStream(new ByteArrayInputStream(image));
      if (contentType == null) {
        contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
      }
      HttpHeaders headers = new HttpHeaders();
      headers.set("Content-Type", contentType);
      log.debug("VolunteerController.getProfilePicture({}) - Image retrieved successfully", id);
      return new ResponseEntity<>(image, headers, HttpStatus.OK);
    } catch (IOException e) {
      log.error("VolunteerController.getProfilePicture({}) - IOException: ", id, e);
      throw new VolunteerControllerException(
              "Volunteer controller IO Exception in getProfilePicture(id)", e);
    } catch (VolunteerServiceException e) {
      log.error("VolunteerController.getProfilePicture({}) - ServiceException: ", id, e);
      throw new VolunteerControllerException("Error while retrieving the image", e);
    }
  }

  /**
   * Retrieves a paginated list of volunteers.
   *
   * @param page the page number to retrieve (default is 0).
   * @param size the number of volunteers per page (default is 10).
   * @return ResponseEntity containing a Page of VolunteerDTOs.
   * @throws VolunteerException if an error occurs while fetching volunteers.
   */
  @GetMapping("/getPageVolunteer")
  public ResponseEntity<Page<VolunteerDTO>> getVolunteers(
          @RequestParam(defaultValue = "0") int page,
          @RequestParam(defaultValue = "10") int size) throws VolunteerException {
    log.debug("Entering VolunteerController.getVolunteers(), page: {}, size: {}", page, size);
    try {
      Pageable pageable = PageRequest.of(page, size);
      Page<VolunteerDTO> volunteers = volunteerService.getAllVolunteers(pageable);
      log.debug("VolunteerController.getVolunteers() - Retrieved {} volunteers",
              volunteers.getTotalElements());
      return ResponseEntity.ok(volunteers);
    } catch (VolunteerServiceException e) {
      log.error("VolunteerController.getVolunteers() - Exception: ", e);
      throw new VolunteerControllerException("Error fetching volunteers", e);
    }
  }

  /**
   * Adds a volunteer to an event.
   *
   * @param volunteerId the ID of the volunteer.
   * @param eventId     the ID of the event.
   * @return ResponseEntity containing a success message.
   */
  @PutMapping("{volunteerId}/event-accept/{eventId}")
  public ResponseEntity<String> addVolunteerToEvent(
          @PathVariable int volunteerId,
          @PathVariable int eventId) {
    log.debug("Entering VolunteerController.addVolunteerToEvent({}, {})", volunteerId, eventId);
    volunteerService.addVolunteerToEvent(volunteerId, eventId);
    log.debug(
            "VolunteerController.addVolunteerToEvent({}, {}) -"
                    + " Volunteer added to event successfully",
            volunteerId, eventId);
    return ResponseEntity.ok("Volunteer added to the event successfully");
  }

  /**
   * Checks if a volunteer is added to an event.
   *
   * @param volunteerId the ID of the volunteer.
   * @param eventId     the ID of the event.
   * @return ResponseEntity containing a boolean indicating if the mapping exists.
   */
  @GetMapping("{volunteerId}/event-accept/{eventId}")
  public ResponseEntity<Boolean> getVolunteerEvent(
          @PathVariable int volunteerId,
          @PathVariable int eventId) {
    log.debug("Entering VolunteerController.getVolunteerEvent({}, {})", volunteerId, eventId);
    Boolean mappingPresent = volunteerService.getVolunteerEvent(volunteerId, eventId);
    log.debug("VolunteerController.getVolunteerEvent({}, {}) - Mapping present: {}",
            volunteerId, eventId, mappingPresent);
    return ResponseEntity.ok(mappingPresent);
  }

  /**
   * Retrieves basic header information of a volunteer.
   *
   * @param id the ID of the volunteer.
   * @return a NotNullResponse wrapping VolunteerDTO containing the volunteer's header information.
   * @throws VolunteerException if an error occurs while fetching the information.
   */
  @GetMapping("volunteer-header/{id}")
  public NotNullResponse<VolunteerDTO> getVolunteerHeader(
          @PathVariable int id) throws VolunteerException {
    log.debug("Entering VolunteerController.getVolunteerHeader({})", id);
    try {
      VolunteerDTO volunteerDetails = volunteerService.getVolunteerHeader(id);
      log.debug("VolunteerController.getVolunteerHeader({}) - Retrieved header info", id);
      return new NotNullResponse<>(volunteerDetails);
    } catch (VolunteerServiceException e) {
      log.error("VolunteerController.getVolunteerHeader({}) - Exception: ", id, e);
      throw new VolunteerControllerException(
              "Volunteer controller exception in getVolunteerHeader(id)", e);
    }
  }

  /**
   * Endpoint to test error response handling specific to VolunteerException.
   *
   * @return a string indicating the error.
   * @throws VolunteerException always thrown to simulate an error.
   */
  @GetMapping("/volunteer-error")
  public String volunteerError() throws VolunteerException {
    log.debug("Entering VolunteerController.volunteerError()");
    throw new VolunteerException("Volunteer exception occurred!");
  }

  /**
   * Endpoint to test global error response handling.
   *
   * @return a string indicating the error.
   * @throws Exception always thrown to simulate a global exception.
   */
  @GetMapping("/global-error")
  public String globalError() throws Exception {
    log.debug("Entering VolunteerController.globalError()");
    throw new Exception("Global exception occurred!");
  }

}
