package com.example.wsa.organiser;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

/**
 * Repository interface for Organiser entities.
 * Provides methods to perform CRUD operations and custom stored procedures.
 */
public interface OrganiserRespository extends JpaRepository<Organiser, Integer> {

  /**
   * Calls the stored procedure 'GetOrganiserProfilePicture' to retrieve
   * the profile picture of an organiser.
   *
   * @param organiserID the ID of the organiser
   * @return a byte array representing the organiser's profile picture
   */
  @Procedure(procedureName = "GetOrganiserProfilePicture")
  byte[] getOrganiserProfilePicture(@Param("organiser_id") Integer organiserID);

  /**
   * Calls the stored procedure 'getPageOrganiser' to retrieve a paginated list of organisers.
   *
   * @param page the page number to retrieve
   * @param size the number of records per page
   * @return a list of Object arrays representing organiser data
   */
  @Procedure(procedureName = "getPageOrganiser")
  List<Object[]> getPageOrganiser(@Param("page") Integer page, @Param("size") Integer size);
}
