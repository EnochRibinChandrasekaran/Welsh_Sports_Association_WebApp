package com.example.wsa.volunteer;

import com.example.wsa.event.EventDTO;
import com.example.wsa.volunteer.exception.VolunteerServiceException;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Interface for Volunteer Service.
 * Provides methods for managing volunteers and retrieving event information.
 */
public interface VolunteerService {

  /**
   * Retrieves a list of upcoming events.
   *
   * @return a list of {@link EventDTO} representing upcoming events
   */
  List<EventDTO> upcomingEvents();

  /**
   * Retrieves a list of approved events with their dates.
   *
   * @return a list of {@link EventDTO} representing approved events and their dates
   * @throws VolunteerServiceException if an error occurs while fetching the events
   */
  List<EventDTO> approvedEventsDates() throws VolunteerServiceException;

  /**
   * Retrieves the details of a volunteer by their ID.
   *
   * @param id the ID of the volunteer
   * @return {@link VolunteerDTO} containing volunteer details
   * @throws VolunteerServiceException if the volunteer is not found or an error occurs
   */
  VolunteerDTO getVolunteerDetails(Integer id) throws VolunteerServiceException;

  /**
   * Updates the information of a volunteer.
   *
   * @param id        the ID of the volunteer to update
   * @param volunteer the {@link VolunteerDTO} containing updated information
   * @return {@link VolunteerDTO} containing the updated volunteer information
   * @throws VolunteerServiceException if the volunteer is not found or an error occurs
   */
  VolunteerDTO updateVolunteerInfo(int id, VolunteerDTO volunteer) throws VolunteerServiceException;

  /**
   * Saves the profile image of a volunteer.
   *
   * @param image the image data in Base64 format
   * @param id    the ID of the volunteer
   * @throws VolunteerServiceException if the volunteer is not found or an error occurs
   */
  void saveImage(String image, int id) throws VolunteerServiceException;

  /**
   * Retrieves the profile picture of a volunteer.
   *
   * @param id the ID of the volunteer
   * @return a byte array containing the image data
   * @throws VolunteerServiceException if the volunteer is not found or an error occurs
   */
  byte[] getProfilePicture(int id) throws VolunteerServiceException;

  /**
   * Retrieves a paginated list of volunteers.
   *
   * @param pageable pagination information
   * @return a {@link Page} of {@link VolunteerDTO} containing volunteers
   * @throws VolunteerServiceException if an error occurs while fetching volunteers
   */
  Page<VolunteerDTO> getAllVolunteers(Pageable pageable) throws VolunteerServiceException;

  /**
   * Adds a volunteer to an event.
   *
   * @param volunteerId the ID of the volunteer
   * @param eventId     the ID of the event
   */
  void addVolunteerToEvent(int volunteerId, int eventId);

  /**
   * Checks if a volunteer is associated with an event.
   *
   * @param volunteerId the ID of the volunteer
   * @param eventId     the ID of the event
   * @return {@code true} if the volunteer is associated with the event, {@code false} otherwise
   */
  Boolean getVolunteerEvent(int volunteerId, int eventId);

  /**
   * Saves a new volunteer with basic information.
   *
   * @param firstName   the first name of the volunteer
   * @param lastName    the last name of the volunteer
   * @param email       the email of the volunteer
   * @param phoneNumber the phone number of the volunteer
   * @return the ID of the newly created volunteer
   */
  Integer saveVolunteer(String firstName, String lastName, String email, String phoneNumber);

  /**
   * Retrieves basic header information of a volunteer.
   *
   * @param id the ID of the volunteer
   * @return {@link VolunteerDTO} containing the volunteer's header information
   * @throws VolunteerServiceException if the volunteer is not found or an error occurs
   */
  VolunteerDTO getVolunteerHeader(int id) throws VolunteerServiceException;
}
