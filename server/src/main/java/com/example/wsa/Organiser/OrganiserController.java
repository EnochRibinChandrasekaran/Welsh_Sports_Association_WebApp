package com.example.wsa.organiser;

import com.example.wsa.organiser.exception.OrganiserControllerException;
import com.example.wsa.organiser.exception.OrganiserException;
import com.example.wsa.organiser.exception.OrganiserServiceException;
import com.example.wsa.utility.NotNullResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for managing organisers.
 * Provides endpoints for organiser-related operations.
 */
@Slf4j
@RestController
@RequestMapping("/api/organiser")
public class OrganiserController {

  private final OrganiserService organiserService;

  /**
   * Constructs a new OrganiserController with the specified OrganiserService.
   *
   * @param organiserService the service used to manage organisers
   */
  @Autowired
  public OrganiserController(OrganiserService organiserService) {
    this.organiserService = organiserService;
  }

  /**
   * Retrieves a list of all organisers.
   *
   * @return a list of OrganiserDTO objects representing the organisers
   */
  @GetMapping
  public List<OrganiserDTO> getOrganiserList() {
    log.debug("Entering getOrganiserList()");
    List<OrganiserDTO> organisers = organiserService.getOrganiserList();
    log.debug("Retrieved {} organisers", organisers.size());
    return organisers;
  }

  /**
   * Retrieves an organiser by their ID.
   *
   * @param id the ID of the organiser
   * @return the OrganiserDTO representing the organiser
   * @throws OrganiserException if an error occurs while fetching the organiser
   */
  @GetMapping("/{id}")
  public OrganiserDTO getOrganiserById(@PathVariable int id) throws OrganiserException {
    try {
      log.debug("Entering getOrganiserById() with id: {}", id);
      OrganiserDTO organiser = organiserService.getOrganiserDetails(id);
      log.debug("Retrieved organiser: {}", organiser);
      return organiser;
    } catch (OrganiserServiceException e) {
      log.error("Error in getOrganiserById() for id: {}", id, e);
      throw new OrganiserControllerException("Error fetching organiser with id: " + id, e);
    }
  }

  /**
   * Uploads a profile picture for the organiser.
   *
   * @param id      the ID of the organiser
   * @param payload the request payload containing the profile image in base64 format
   * @return a ResponseEntity indicating the result of the upload operation
   * @throws OrganiserException if an error occurs while uploading the image
   */
  @PostMapping("/profile-picture/{id}")
  public ResponseEntity<String> uploadProfilePicture(@PathVariable int id,
                                                     @RequestBody Map<String, String> payload)
          throws OrganiserException {
    try {
      log.debug("Entering uploadProfilePicture() with id: {}", id);
      String base64Image = payload.get("profileImage");
      String imageData = base64Image.split(",")[1];
      organiserService.saveImage(imageData, id);
      log.debug("Profile picture uploaded for organiser id: {}", id);
      return ResponseEntity.ok("Image uploaded");
    } catch (OrganiserServiceException e) {
      log.error("Error in uploadProfilePicture() for id: {}", id, e);
      throw new OrganiserControllerException("Error uploading profile picture for id: " + id, e);
    }
  }

  /**
   * Updates the information of an organiser.
   *
   * @param id           the ID of the organiser to update
   * @param organiserDTO the new data for the organiser
   * @return a ResponseEntity containing the updated OrganiserDTO
   * @throws OrganiserException if an error occurs while updating the organiser
   */
  @PutMapping("/{id}")
  public ResponseEntity<OrganiserDTO> updateOrganiserInfo(@PathVariable int id,
                                                          @RequestBody OrganiserDTO organiserDTO)
          throws OrganiserException {
    try {
      log.debug("Entering updateOrganiserInfo() with id: {}", id);
      OrganiserDTO updatedOrganiserInfo = organiserService.updateOrganiserInfo(id, organiserDTO);
      log.debug("Organiser info updated for id: {}", id);
      return ResponseEntity.ok(updatedOrganiserInfo);
    } catch (OrganiserServiceException e) {
      log.error("Error in updateOrganiserInfo() for id: {}", id, e);
      throw new OrganiserControllerException("Error updating organiser info for id: " + id, e);
    }
  }

  /**
   * Retrieves the profile picture of an organiser.
   *
   * @param id the ID of the organiser
   * @return a ResponseEntity containing the image bytes and headers
   * @throws OrganiserException if an error occurs while fetching the profile picture
   */
  @GetMapping("/profile-picture/{id}")
  public ResponseEntity<byte[]> getProfilePicture(@PathVariable int id) throws OrganiserException {
    log.debug("Entering getProfilePicture() with id: {}", id);
    byte[] image = organiserService.getProfilePicture(id);
    if (image == null) {
      log.warn("Profile picture not found for organiser id: {}", id);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    String contentType;
    try {
      contentType = URLConnection.guessContentTypeFromStream(new ByteArrayInputStream(image));
    } catch (IOException e) {
      log.error("Error in getProfilePicture() for id: {}", id, e);
      throw new OrganiserControllerException("IO Exception in getProfilePicture(id)", e);
    }
    if (contentType == null) {
      contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
    }
    HttpHeaders headers = new HttpHeaders();
    headers.set("Content-Type", contentType);
    log.debug("Profile picture retrieved for organiser id: {}", id);
    return new ResponseEntity<>(image, headers, HttpStatus.OK);
  }

  /**
   * Retrieves the header information for an organiser.
   *
   * @param id the ID of the organiser
   * @return a NotNullResponse containing the OrganiserDTO header information
   * @throws OrganiserControllerException if an error occurs while fetching the organiser header
   */
  @GetMapping("organiser-header/{id}")
  public NotNullResponse<OrganiserDTO> getOrganiserHeader(@PathVariable int id)
          throws OrganiserControllerException {
    try {
      log.debug("Entering getOrganiserHeader() with id: {}", id);
      OrganiserDTO organiserHeader = organiserService.getOrganiserHeader(id);
      log.debug("Retrieved organiser header for id: {}", id);
      return new NotNullResponse<>(organiserHeader);
    } catch (OrganiserServiceException e) {
      log.error("Error in getOrganiserHeader() for id: {}", id, e);
      throw new OrganiserControllerException("Error fetching organiser header for id: " + id, e);
    }
  }

  /**
   * Endpoint to test error response.
   *
   * @throws OrganiserException always thrown to simulate an error
   */
  @GetMapping("/organiser-error")
  public String organiserError() throws OrganiserException {
    log.debug("Entering organiserError()");
    throw new OrganiserException("Organiser exception occurred!");
  }

  /**
   * Updates the volunteer event approval.
   *
   * @param eventId     the ID of the organiser
   * @param volunteerId the ID of the volunteer
   * @return a ResponseEntity containing the boolean
   * @throws OrganiserException if an error occurs while updating the volunteer approval
   */
  @PutMapping("event/{eventId}/volunteer/{volunteerId}")
  public ResponseEntity<Boolean> volunteerEventApproval(
          @PathVariable int eventId, @PathVariable String volunteerId)
          throws OrganiserException {
    log.debug("Entering volunteerEventApproval() with event_id: {}", eventId);
    try {
      boolean approved = organiserService.volunteerEventApproval(
              eventId, Integer.parseInt(volunteerId));
      log.debug("Volunteer: {} approved for Event: {} : {}", volunteerId, eventId, approved);
      return ResponseEntity.ok(approved);
    } catch (OrganiserServiceException e) {
      log.error("Error in volunteerEventApproval() for event_id: {}", eventId, e);
      throw new OrganiserControllerException("Error updating volunteer and event mapping: ", e);
    }
  }
}
