package com.example.wsa.organiser;

import com.example.wsa.organiser.exception.OrganiserRepositoryException;
import com.example.wsa.organiser.exception.OrganiserServiceException;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Organiser Service Implementation Class - implementation logic.
 */
@Slf4j
@Service
public class OrganiserServiceImpl implements OrganiserService {

  private final OrganiserRespository organiserRespository;


  private final EntityManager entityManager;

  /**
   * Constructs a new instance of {@code OrganiserServiceImpl}.
   *
   * @param organiserRespository the repository for organiser data
   * @param entityManager        the entity manager for JPA operations
   */
  @Autowired
  public OrganiserServiceImpl(OrganiserRespository organiserRespository,
                              EntityManager entityManager) {
    this.organiserRespository = organiserRespository;
    this.entityManager = entityManager;
  }

  /**
   * Method to fetch Organiser List using Organiser Repository method findAll().
   */
  @Override
  public List<OrganiserDTO> getOrganiserList() {
    return organiserRespository.findAll()
            .stream().map(this::organiserDTOMapper).collect((Collectors.toList()));
  }

  /**
   * Method to fetch Organiser Details using Organiser Repository method findById(id).
   */
  @Override
  public OrganiserDTO getOrganiserDetails(Integer id) throws OrganiserServiceException {
    try {
      Organiser organiserDetails = organiserRespository
              .findById(id).orElseThrow(() ->
                      new OrganiserRepositoryException("Organiser not found with id : " + id));
      log.debug("OrganiserServiceImpl.getOrganiserDetails() : {}", organiserDetails);
      return organiserDTOMapper(organiserDetails);
    } catch (OrganiserRepositoryException e) {
      throw new OrganiserServiceException("Organiser not found with id: " + id, e);
    } catch (Exception e) {
      throw new OrganiserServiceException(
              "An unexpected error occurred while fetching organiser details", e);
    }
  }

  /**
   * Method to save Organiser Profile Picture using organiser
   * Repository findById(id) and save(organiser).
   */
  @Override
  public void saveImage(String imageData, int id) throws OrganiserServiceException {
    try {
      Organiser organiserDetails = organiserRespository.findById(id).orElseThrow(() ->
              new OrganiserRepositoryException("Organiser not found with id: " + id));
      byte[] imageBytes = Base64.getDecoder().decode(imageData);
      organiserDetails.setLogo(imageBytes);
      organiserRespository.save(organiserDetails);
      log.debug("OrganiserServiceImpl.saveImage() : {}", id);
    } catch (OrganiserRepositoryException e) {
      log.error("OrganiserServiceImpl.saveImage(): {}", e.getMessage());
      throw new OrganiserServiceException("Organiser not found with id: " + id, e);
    } catch (Exception e) {
      log.error("OrganiserServiceImpl.saveImage() Exception:  {}", e.getMessage());
      throw new OrganiserServiceException(
              "An unexpected error occurred while fetching Organiser details", e);
    }

  }

  /**
   * Method to get Organisation Profile Picture using organiserRepository
   * stored procedure GetOrganiserProfilePicture.
   */
  @Override
  public byte[] getProfilePicture(int id) {
    StoredProcedureQuery query = entityManager
            .createStoredProcedureQuery("GetOrganiserProfilePicture");
    query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
    query.setParameter(1, id);
    return (byte[]) query.getSingleResult();
  }

  /**
   * Method to fetch Organisation Details using organiserRepository findById(id)
   * and update the Organisation details.
   */
  @Override
  public OrganiserDTO updateOrganiserInfo(int id, OrganiserDTO organiserDTO)
          throws OrganiserServiceException {
    Organiser organiserDetails = null;
    try {
      organiserDetails = organiserRespository.findById(id).orElseThrow(() ->
              new OrganiserRepositoryException("Organiser not found with id: " + id));
      System.out.println(organiserDetails);
      organiserDetails.setActivated(false);
      organiserDetails.setPostCode(organiserDTO.getPostCode());
      organiserDetails.setCharity(organiserDTO.getCharity());
      organiserDetails.setAddress(organiserDTO.getAddress());
      organiserDetails.setTelephone(organiserDTO.getTelephone());
      organiserDetails.setEmail(organiserDTO.getEmail());
      organiserDetails.setWebsite(organiserDTO.getWebsite());
      organiserDetails.setMainContactName(organiserDTO.getMainContactName());
      organiserDetails.setMainContactPosition(organiserDTO.getMainContactPosition());
      organiserDetails.setMainContactMobileNumber(organiserDTO.getMainContactMobileNumber());
      organiserDetails.setFoundingDate(organiserDTO.getFoundingDate());
      organiserDetails.setNumberOfMembers(organiserDTO.getNumberOfMembers());
      organiserDetails.setAssociatedClubs(organiserDTO.getAssociatedClubs());
      organiserDetails.setTurnover(organiserDTO.getTurnover());
      organiserDetails.setMembershipCategory(organiserDTO.getMembershipCategory());
      organiserDetails.setNewsletter(organiserDTO.getNewsletter());
      System.out.println(organiserDetails);
      log.debug("OrganiserServiceImpl.updateOrganiserInfo() : {}", organiserDetails);
      organiserRespository.save(organiserDetails);
      return organiserDTOMapper(organiserDetails);
    } catch (OrganiserRepositoryException e) {
      log.error("OrganiserServiceImpl.updateOrganiserInfo(): {}", e.getMessage());
      throw new OrganiserServiceException("Organisation not found with id: " + id, e);
    } catch (Exception e) {
      log.error("OrganiserServiceImpl.updateOrganiserInfo() Exception : {}", e.getMessage());
      throw new OrganiserServiceException(
              "An unexpected error occurred while fetching organiser details", e);
    }

  }

  /**
   * Method to fetch Organisation Details using organiserRepository findById(id)
   * and get the Organisation details.
   */
  @Override
  public OrganiserDTO getOrganiserHeader(int id) throws OrganiserServiceException {
    try {
      Organiser organiserDetails = organiserRespository.findById(id).orElseThrow(() ->
              new OrganiserRepositoryException("Organiser not found with id: " + id));
      log.debug("OrganiserServiceImpl.getOrganiserHeader() : {}", organiserDetails);
      return convertToDTOHeader(organiserDetails);
    } catch (OrganiserRepositoryException e) {
      log.error("OrganiserServiceImpl.getOrganiserHeader():"
              + "OrganiserRepositoryException {}", e.getMessage());
      throw new OrganiserServiceException("Organiser not found with id: " + id, e);
    } catch (Exception e) {
      log.error("OrganiserServiceImpl.getOrganiserHeader() Exception:  {}", e.getMessage());
      throw new OrganiserServiceException("An unexpected error occurred"
              + " while fetching organiser details", e);
    }
  }

  /**
   * Method to update Volunteer Event Approval using entity manager
   * stored procedure ApproveVolunteerEvent.
   */
  @Override
  public boolean volunteerEventApproval(int eventId, int volunteerId)
          throws OrganiserServiceException {
    try {
      log.debug("OrganiserServiceImpl.volunteerEventApproval() Volunteer ID : {} Event ID : {}",
              volunteerId, eventId);
      StoredProcedureQuery query = entityManager
              .createStoredProcedureQuery("ApproveVolunteerEvent");
      query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
      query.registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN);
      query.setParameter(1, volunteerId);
      query.setParameter(2, eventId);
      return (boolean) query.getSingleResult();
    } catch (Exception e) {
      log.error("VolunteerEventApproval():{}", e.getMessage());
      throw new OrganiserServiceException("An error occurred when updating "
              + "volunteer event approval table", e);
    }
  }

  private OrganiserDTO convertToDTOHeader(Organiser organiserDetails) {
    OrganiserDTO organiserDTO = new OrganiserDTO();
    organiserDTO.setLogo(organiserDetails.getLogo());
    organiserDTO.setCompanyName(organiserDetails.getCompanyName());
    organiserDTO.setWebsite(organiserDetails.getWebsite());
    return organiserDTO;
  }

  /**
   * Organiser -> OrganiserDTO Mapper.
   */
  private OrganiserDTO organiserDTOMapper(Organiser organiser) {
    if (organiser == null) {
      throw new NullPointerException("Organiser object is null");
    }
    return new OrganiserDTO(
            organiser.getId(),
            organiser.getCompanyName(),
            organiser.getLogo(),
            organiser.getPostCode(),
            organiser.getCharity(),
            organiser.getAddress(),
            organiser.getTelephone(),
            organiser.getEmail(),
            organiser.getWebsite(),
            organiser.getMainContactName(),
            organiser.getMainContactPosition(),
            organiser.getMainContactMobileNumber(),
            organiser.getFoundingDate(),
            organiser.getNumberOfMembers(),
            organiser.getAssociatedClubs(),
            organiser.getTurnover(),
            organiser.getMembershipCategory(),
            organiser.getPreferredLanguage(),
            organiser.getNewsletter(),
            organiser.getActivated()
    );
  }

  /**
   * Method to get Volunteers Profile Picture using volunteerRepository stored
   * procedure GetVolunteerProfilePicture.
   */
  @Override
  public Page<OrganiserDTO> getPageOrganiser(Pageable pageable) {
    // Create a stored procedure query
    StoredProcedureQuery query = entityManager
            .createStoredProcedureQuery("getPageOrganiser", Organiser.class);

    // Register parameters (if your stored procedure requires them)
    query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
    query.registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN);

    // Set the parameters for pagination
    query.setParameter(1, pageable.getPageNumber());
    query.setParameter(2, pageable.getPageSize());

    // Get the list of Volunteer entities directly
    @SuppressWarnings("unchecked")
    List<Organiser> organisers = query.getResultList();

    // Convert the Volunteer entities to VolunteerDTOs using the existing
    // convertToDto method
    List<OrganiserDTO> organiserDTOS = organisers.stream()
            .map(this::organiserDTOMapper)
            .collect(Collectors.toList());

    // Return the paginated list of VolunteerDTOs
    return new PageImpl<>(organiserDTOS, pageable, organisers.size());
  }

  @Override
  public Integer saveAuthOrganiser(String email_id, String telephone_no, String post_code,
                                   String address_link, String website_link, String founding_date,
                                   String associated_clubs, String number_of_members) {
    StoredProcedureQuery query = entityManager.createStoredProcedureQuery("SaveOrganiserAuth");

    // Registering the stored procedure parameters
    query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);  // email
    query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);  // phone number
    query.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);  // post code
    query.registerStoredProcedureParameter(4, String.class, ParameterMode.IN);  // address
    query.registerStoredProcedureParameter(5, String.class, ParameterMode.IN);  // website
    query.registerStoredProcedureParameter(6, String.class, ParameterMode.IN);  // founding date
    query.registerStoredProcedureParameter(7, String.class, ParameterMode.IN);  // associated clubs
    query.registerStoredProcedureParameter(8, String.class, ParameterMode.IN);  // number of members
    query.registerStoredProcedureParameter(
            9, Integer.class, ParameterMode.OUT); // return organiser id

    // Setting the parameters with the provided values
    query.setParameter(1, email_id);
    query.setParameter(2, telephone_no);
    query.setParameter(3, post_code);
    query.setParameter(4, address_link);
    query.setParameter(5, website_link);
    query.setParameter(6, founding_date);
    query.setParameter(7, associated_clubs);
    query.setParameter(8, number_of_members);

    // Execute the stored procedure
    query.execute();

    // Retrieve the output parameter value (organiser ID)
    return (Integer) query.getOutputParameterValue(9);
  }

  @Override
  public Integer getOrgVolId(Integer id, String tableName) {
    StoredProcedureQuery query = entityManager.createStoredProcedureQuery("GetVolunteerOrganiser");

    query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
    query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
    query.registerStoredProcedureParameter(3, Integer.class, ParameterMode.OUT);

    query.setParameter(1, id);
    query.setParameter(2, tableName);

    query.execute();

    return (Integer) query.getOutputParameterValue(3);
  }
}
