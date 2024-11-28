package com.example.wsa.volunteer;

import com.example.wsa.event.EventDTO;
import com.example.wsa.event.EventRepository;
import com.example.wsa.volunteer.exception.VolunteerRepositoryException;
import com.example.wsa.volunteer.exception.VolunteerServiceException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Implementation of the {@link VolunteerService} interface.
 * Provides business logic for managing volunteers, including fetching,
 * updating, and associating volunteers with events.
 */
@Slf4j
@Service
public class VolunteerServiceImpl implements VolunteerService {

  private final VolunteerRepository volunteerRepository;
  private final EventRepository eventRepository;
  private final JdbcTemplate jdbcTemplate;
  private final EntityManager entityManager;

  /**
   * Constructs a new instance of {@code VolunteerServiceImpl}.
   *
   * @param volunteerRepository the repository for volunteer data
   * @param eventRepository     the repository for event data
   * @param jdbcTemplate        the JDBC template for database operations
   * @param entityManager       the entity manager for JPA operations
   */
  @Autowired
  public VolunteerServiceImpl(VolunteerRepository volunteerRepository,
                              EventRepository eventRepository,
                              JdbcTemplate jdbcTemplate,
                              EntityManager entityManager) {
    this.volunteerRepository = volunteerRepository;
    this.eventRepository = eventRepository;
    this.jdbcTemplate = jdbcTemplate;
    this.entityManager = entityManager;
  }

  /**
   * Retrieves a list of upcoming events.
   *
   * @return a list of {@link EventDTO} representing upcoming events
   */
  @Override
  public List<EventDTO> upcomingEvents() {
    log.debug("Fetching upcoming events");
    List<Object[]> results = eventRepository.findUpcomingEvents();
    return results.stream()
            .map(this::upcomingEventsMapper)
            .collect(Collectors.toList());
  }

  /**
   * Retrieves dates of approved events.
   *
   * @return a list of {@link EventDTO} containing approved event dates
   * @throws VolunteerServiceException if an error occurs while fetching data
   */
  @Override
  public List<EventDTO> approvedEventsDates() throws VolunteerServiceException {
    try {
      log.debug("Fetching approved events dates");
      List<Object[]> results = eventRepository.findApprovedEventsDates();
      return results.stream()
              .map(this::approvedEventsDatesMapper)
              .collect(Collectors.toList());
    } catch (Exception e) {
      log.error("Error fetching approved events dates", e);
      throw new VolunteerRepositoryException("Error fetching Events in the Repository", e);
    }
  }

  /**
   * Retrieves volunteer details by ID.
   *
   * @param id the ID of the volunteer
   * @return a {@link VolunteerDTO} containing volunteer details
   * @throws VolunteerServiceException if the volunteer is not found or an error occurs
   */
  @Override
  public VolunteerDTO getVolunteerDetails(Integer id) throws VolunteerServiceException {
    try {
      log.debug("Fetching volunteer details for ID: {}", id);
      Volunteer volunteerDetails = volunteerRepository.findById(id)
              .orElseThrow(() -> new VolunteerRepositoryException(
                      "Volunteer not found with id: " + id));
      return convertToDto(volunteerDetails);
    } catch (VolunteerRepositoryException e) {
      log.error("Volunteer not found with id: {}", id, e);
      throw new VolunteerServiceException("Volunteer not found with id: " + id, e);
    } catch (Exception e) {
      log.error("Unexpected error while fetching volunteer details for id: {}", id, e);
      throw new VolunteerServiceException(
              "An unexpected error occurred while fetching volunteer details", e);
    }
  }

  /**
   * Updates volunteer information.
   *
   * @param id           the ID of the volunteer to update
   * @param volunteerDTO the new volunteer data
   * @return the updated {@link VolunteerDTO}
   * @throws VolunteerServiceException if the volunteer is not found or an error occurs
   */
  @Override
  public VolunteerDTO updateVolunteerInfo(int id, VolunteerDTO volunteerDTO)
          throws VolunteerServiceException {
    try {
      log.debug("Updating volunteer info for ID: {}", id);
      Volunteer volunteerDetails = volunteerRepository.findById(id)
              .orElseThrow(() -> new VolunteerRepositoryException(
                      "Volunteer not found with id: " + id));

      // Update volunteer details
      volunteerDetails.setFirstName(volunteerDTO.getFirstName());
      volunteerDetails.setLastName(volunteerDTO.getLastName());
      volunteerDetails.setGender(volunteerDTO.getGender());
      volunteerDetails.setDob(volunteerDTO.getDob());
      volunteerDetails.setEmail(volunteerDTO.getEmail());
      volunteerDetails.setPhoneNumber(volunteerDTO.getPhoneNumber());
      volunteerDetails.setAddress(volunteerDTO.getAddress());
      volunteerDetails.setPostalCode(volunteerDTO.getPostalCode());
      volunteerDetails.setOccupation(volunteerDTO.getOccupation());
      volunteerDetails.setQualifications(String.join(",", volunteerDTO.getQualifications()));
      volunteerDetails.setAvailability(String.join(",", volunteerDTO.getAvailability()));
      volunteerDetails.setRoles(String.join(",", volunteerDTO.getRoles()));
      volunteerDetails.setDbs(volunteerDTO.getDbs());
      volunteerDetails.setAccessibilityEnhancement(
              String.join(",", volunteerDTO.getAccessibilityEnhancement()));
      volunteerDetails.setAbout(volunteerDTO.getAbout());
      volunteerDetails.setEmergencyContactName(volunteerDTO.getEmergencyContactName());
      volunteerDetails.setEmergencyPhoneNumber(volunteerDTO.getEmergencyPhoneNumber());
      volunteerDetails.setEmergencyRelationship(volunteerDTO.getEmergencyRelationship());

      Volunteer updatedVolunteer = volunteerRepository.save(volunteerDetails);
      log.debug("Volunteer info updated for ID: {}", id);
      return convertToDto(updatedVolunteer);
    } catch (VolunteerRepositoryException e) {
      log.error("Volunteer not found with id: {}", id, e);
      throw new VolunteerServiceException("Volunteer not found with id: " + id, e);
    } catch (Exception e) {
      log.error("Unexpected error while updating volunteer info for id: {}", id, e);
      throw new VolunteerServiceException(
              "An unexpected error occurred while updating volunteer details", e);
    }
  }

  /**
   * Saves a volunteer's profile picture.
   *
   * @param image the base64-encoded image data
   * @param id    the ID of the volunteer
   * @throws VolunteerServiceException if the volunteer is not found or an error occurs
   */
  @Override
  public void saveImage(String image, int id) throws VolunteerServiceException {
    try {
      log.debug("Saving profile image for volunteer ID: {}", id);
      Volunteer volunteerDetails = volunteerRepository.findById(id)
              .orElseThrow(() -> new VolunteerRepositoryException(
                      "Volunteer not found with id: " + id));
      byte[] imageBytes = Base64.getDecoder().decode(image);
      volunteerDetails.setImage(imageBytes);
      volunteerRepository.save(volunteerDetails);
      log.debug("Profile image saved for volunteer ID: {}", id);
    } catch (VolunteerRepositoryException e) {
      log.error("Volunteer not found with id: {}", id, e);
      throw new VolunteerServiceException("Volunteer not found with id: " + id, e);
    } catch (Exception e) {
      log.error("Unexpected error while saving profile image for id: {}", id, e);
      throw new VolunteerServiceException(
              "An unexpected error occurred while saving volunteer image", e);
    }
  }

  /**
   * Retrieves a volunteer's profile picture.
   *
   * @param id the ID of the volunteer
   * @return a byte array containing the image data
   * @throws VolunteerServiceException if an error occurs while fetching the image
   */
  @Override
  public byte[] getProfilePicture(int id) throws VolunteerServiceException {
    try {
      log.debug("Fetching profile picture for volunteer ID: {}", id);
      StoredProcedureQuery query = entityManager
              .createStoredProcedureQuery("GetVolunteerProfilePicture");
      query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
      query.setParameter(1, id);
      return (byte[]) query.getSingleResult();
    } catch (Exception e) {
      log.error("Error fetching profile picture for volunteer ID: {}", id, e);
      throw new VolunteerRepositoryException("Error Fetching Image from the DB", e);
    }
  }

  /**
   * Adds a volunteer to an event.
   *
   * @param volunteerId the ID of the volunteer
   * @param eventId     the ID of the event
   */
  @Override
  @Transactional
  public void addVolunteerToEvent(int volunteerId, int eventId) {
    log.debug("Adding volunteer ID: {} to event ID: {}", volunteerId, eventId);
    String sql = "INSERT INTO volunteer_event (volunteer_id, event_id) VALUES (?, ?)";
    jdbcTemplate.update(sql, volunteerId, eventId);
  }

  /**
   * Checks if a volunteer is associated with an event.
   *
   * @param volunteerId the ID of the volunteer
   * @param eventId     the ID of the event
   * @return {@code true} if the association exists; {@code false} otherwise
   */
  @Override
  public Boolean getVolunteerEvent(int volunteerId, int eventId) {
    log.debug("Checking association between volunteer ID: {} and event ID: {}",
            volunteerId, eventId);
    String sql = "SELECT EXISTS "
            + "(SELECT 1 FROM volunteer_event WHERE volunteer_id = ? AND event_id = ?)";
    return jdbcTemplate.queryForObject(sql, new Object[]{volunteerId, eventId}, Boolean.class);
  }

  /**
   * Retrieves a paginated list of volunteers.
   *
   * @param pageable the pagination information
   * @return a {@link Page} of {@link VolunteerDTO}
   * @throws VolunteerServiceException if an error occurs while fetching volunteers
   */
  @Override
  public Page<VolunteerDTO> getAllVolunteers(Pageable pageable) throws VolunteerServiceException {
    try {
      log.debug("Fetching all volunteers with pagination: page {}, size {}",
              pageable.getPageNumber(), pageable.getPageSize());

      // Create a stored procedure query
      StoredProcedureQuery query = entityManager
              .createStoredProcedureQuery("getPageVolunteer", Volunteer.class);

      // Register parameters
      query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
      query.registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN);

      // Set parameters
      query.setParameter(1, pageable.getPageNumber());
      query.setParameter(2, pageable.getPageSize());

      // Execute query and get results
      @SuppressWarnings("unchecked")
      List<Volunteer> volunteers = query.getResultList();

      // Convert to DTOs
      List<VolunteerDTO> volunteerDTOs = volunteers.stream()
              .map(this::convertToDto)
              .collect(Collectors.toList());

      log.debug("Fetched {} volunteers", volunteerDTOs.size());
      return new PageImpl<>(volunteerDTOs, pageable, volunteers.size());
    } catch (Exception e) {
      log.error("Error fetching volunteers", e);
      throw new VolunteerRepositoryException("Error Fetching Volunteers", e);
    }
  }

  /**
   * Saves a new volunteer's basic information.
   *
   * @param firstName   the first name of the volunteer
   * @param lastName    the last name of the volunteer
   * @param email       the email of the volunteer
   * @param phoneNumber the phone number of the volunteer
   * @return the ID of the newly saved volunteer
   */
  @Override
  public Integer saveVolunteer(String firstName, String lastName,
                               String email, String phoneNumber) {
    log.debug("Saving new volunteer: {} {}", firstName, lastName);
    StoredProcedureQuery query = entityManager.createStoredProcedureQuery("SaveVolunteerAuth");

    // Register parameters
    query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN); // first name
    query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN); // last name
    query.registerStoredProcedureParameter(3, String.class, ParameterMode.IN); // email
    query.registerStoredProcedureParameter(4, String.class, ParameterMode.IN); // phone number
    query.registerStoredProcedureParameter(5, Integer.class, ParameterMode.OUT); // volunteer id

    // Set input parameters
    query.setParameter(1, firstName);
    query.setParameter(2, lastName);
    query.setParameter(3, email);
    query.setParameter(4, phoneNumber);

    // Execute the stored procedure
    query.execute();

    Integer volunteerId = (Integer) query.getOutputParameterValue(5);
    log.debug("New volunteer saved with ID: {}", volunteerId);
    return volunteerId;
  }

  /**
   * Retrieves basic header information of a volunteer.
   *
   * @param id the ID of the volunteer
   * @return a {@link VolunteerDTO} containing header information
   * @throws VolunteerServiceException if the volunteer is not found or an error occurs
   */
  @Override
  public VolunteerDTO getVolunteerHeader(int id) throws VolunteerServiceException {
    try {
      log.debug("Fetching volunteer header for ID: {}", id);
      Volunteer volunteerDetails = volunteerRepository.findById(id)
              .orElseThrow(() -> new VolunteerRepositoryException(
                      "Volunteer not found with id: " + id));
      return convertToDTOHeader(volunteerDetails);
    } catch (VolunteerRepositoryException e) {
      log.error("Volunteer not found with id: {}", id, e);
      throw new VolunteerServiceException("Volunteer not found with id: " + id, e);
    } catch (Exception e) {
      log.error("Unexpected error while fetching volunteer header for id: {}", id, e);
      throw new VolunteerServiceException(
              "An unexpected error occurred while fetching volunteer details", e);
    }
  }

  /**
   * Converts a {@link Volunteer} entity to a {@link VolunteerDTO}.
   *
   * @param volunteer the volunteer entity
   * @return the volunteer DTO
   */
  private VolunteerDTO convertToDto(Volunteer volunteer) {
    return new VolunteerDTO(
            volunteer.getId(),
            volunteer.getFirstName(),
            volunteer.getLastName(),
            volunteer.getGender(),
            volunteer.getDob(),
            volunteer.getEmail(),
            volunteer.getPhoneNumber(),
            volunteer.getAddress(),
            volunteer.getPostalCode(),
            volunteer.getOccupation(),
            Arrays.asList(volunteer.getQualifications().split(",")),
            Arrays.asList(volunteer.getAvailability().split(",")),
            Arrays.asList(volunteer.getRoles().split(",")),
            volunteer.getDbs(),
            Arrays.asList(volunteer.getAccessibilityEnhancement().split(",")),
            volunteer.getAbout(),
            volunteer.getRewardsEarned(),
            volunteer.getEmergencyContactName(),
            volunteer.getEmergencyPhoneNumber(),
            volunteer.getEmergencyRelationship(),
            volunteer.getMemberStatus(),
            volunteer.getRating(),
            volunteer.getEventAttended(),
            volunteer.getMembershipLevel(),
            volunteer.getImage()
    );
  }

  /**
   * Converts a {@link Volunteer} entity to a {@link VolunteerDTO} containing header information.
   *
   * @param volunteerDetails the volunteer entity
   * @return the volunteer DTO with header information
   */
  private VolunteerDTO convertToDTOHeader(Volunteer volunteerDetails) {
    VolunteerDTO volunteerDTO = new VolunteerDTO();
    volunteerDTO.setImage(volunteerDetails.getImage());
    volunteerDTO.setFirstName(volunteerDetails.getFirstName());
    return volunteerDTO;
  }

  /**
   * Maps the result of an approved events query to an {@link EventDTO}.
   *
   * @param result the query result object array
   * @return the event DTO
   */
  private EventDTO approvedEventsDatesMapper(Object[] result) {
    Integer id = (Integer) result[0];
    String name = (String) result[1];
    LocalDate date = ((Date) result[2]).toLocalDate();
    return new EventDTO(id, name, date);
  }

  /**
   * Maps the result of an upcoming events query to an {@link EventDTO}.
   *
   * @param result the query result object array
   * @return the event DTO
   */
  private EventDTO upcomingEventsMapper(Object[] result) {
    Integer id = (Integer) result[0];
    String name = (String) result[1];
    byte[] image = (byte[]) result[2];
    String postalCode = (String) result[3];
    String city = (String) result[4];
    LocalDate date = ((Date) result[5]).toLocalDate();
    String dayOfWeek = (String) result[6];
    return new EventDTO(id, name, image, postalCode, city, date, dayOfWeek);
  }
}
