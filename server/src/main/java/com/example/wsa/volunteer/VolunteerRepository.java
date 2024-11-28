package com.example.wsa.volunteer;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing {@link Volunteer} entities.
 * <p>
 * Provides methods to interact with the database using stored procedures for
 * specific operations like fetching a volunteer's profile picture and retrieving
 * a paginated list of volunteers.
 * </p>
 */
@Repository
public interface VolunteerRepository extends JpaRepository<Volunteer, Integer> {

  /**
   * Retrieves the profile picture of a volunteer by their ID using a stored procedure.
   *
   * @param volunteerID the ID of the volunteer whose profile picture is to be retrieved.
   * @return a byte array representing the volunteer's profile picture,or {@code null} if not found.
   */
  @Procedure(procedureName = "GetVolunteerProfilePicture")
  byte[] getVolunteerProfilePicture(@Param("volunteer_id") Integer volunteerID);

  /**
   * Retrieves a paginated list of volunteers using a stored procedure.
   *
   * @param page the page number to retrieve (zero-based).
   * @param size the number of volunteers per page.
   * @return a list of objects representing the volunteers for the specified page.
   */
  @Procedure(procedureName = "getPageVolunteer")
  List<Object[]> getPageVolunteer(@Param("page") Integer page, @Param("size") Integer size);
}
