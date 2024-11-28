package com.example.wsa.organiser;

import com.example.wsa.organiser.exception.OrganiserException;
import com.example.wsa.organiser.exception.OrganiserServiceException;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service interface for organiser-related operations.
 */
public interface OrganiserService {

  /**
   * Retrieves a list of all organisers.
   *
   * @return a list of OrganiserDTO objects
   */
  List<OrganiserDTO> getOrganiserList();

  /**
   * Retrieves the details of an organiser by ID.
   *
   * @param id the ID of the organiser
   * @return the OrganiserDTO object representing the organiser
   * @throws OrganiserServiceException if the organiser is not found or an error occurs
   */
  OrganiserDTO getOrganiserDetails(Integer id) throws OrganiserServiceException;

  /**
   * Saves the organiser's profile picture.
   *
   * @param imageData the image data in Base64 string format
   * @param id        the ID of the organiser
   * @throws OrganiserServiceException if the organiser is not found or an error occurs
   */
  void saveImage(String imageData, int id) throws OrganiserServiceException;

  /**
   * Retrieves the profile picture of an organiser.
   *
   * @param id the ID of the organiser
   * @return a byte array representing the organiser's profile picture
   */
  byte[] getProfilePicture(int id);

  /**
   * Updates the information of an organiser.
   *
   * @param id           the ID of the organiser to update
   * @param organiserDTO the new organiser data
   * @return the updated OrganiserDTO object
   * @throws OrganiserServiceException if the organiser is not found or an error occurs
   */
  OrganiserDTO updateOrganiserInfo(int id, OrganiserDTO organiserDTO)
          throws OrganiserServiceException;

  /**
   * Retrieves a paginated list of organisers.
   *
   * @param pageable the pagination information
   * @return a Page containing OrganiserDTO objects
   */
  Page<OrganiserDTO> getPageOrganiser(Pageable pageable);

  /**
   * Saves authentication details for an organiser.
   *
   * @param email_id          the email ID
   * @param telephone_no      the telephone number
   * @param post_code         the postal code
   * @param address_link      the address
   * @param website_link      the website link
   * @param founding_date     the founding date
   * @param associated_clubs  associated clubs
   * @param number_of_members number of members
   * @return the ID of the saved organiser
   */
  Integer saveAuthOrganiser(String email_id, String telephone_no, String post_code,
                            String address_link, String website_link, String founding_date,
                            String associated_clubs, String number_of_members);

  /**
   * Retrieves the organiser or volunteer ID.
   *
   * @param id        the ID to look up
   * @param tableName the table name ('organiser' or 'volunteer')
   * @return the organiser or volunteer ID
   */
  Integer getOrgVolId(Integer id, String tableName);

  /**
   * Retrieves header information for an organiser.
   *
   * @param id the ID of the organiser
   * @return the OrganiserDTO containing header information
   * @throws OrganiserServiceException if the organiser is not found or an error occurs
   */
  OrganiserDTO getOrganiserHeader(int id) throws OrganiserServiceException;

  /**
   * Updates the volunteer event approval.
   *
   * @param eventId     the ID of the organiser
   * @param volunteerId the ID of the volunteer
   * @return a boolean if updated
   * @throws OrganiserServiceException if an error occurs while updating the volunteer approval
   */
  boolean volunteerEventApproval(int eventId, int volunteerId) throws OrganiserServiceException;

}
