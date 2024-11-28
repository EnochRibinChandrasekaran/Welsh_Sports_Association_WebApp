package com.example.wsa.organiser;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.wsa.organiser.exception.OrganiserServiceException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.StoredProcedureQuery;
import java.time.LocalDate;
import java.util.Base64;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * Test class for {@link OrganiserServiceImpl}.
 * Contains unit tests for the OrganiserServiceImpl methods.
 */
public class OrganiserServiceImplTest {

  @Mock
  private OrganiserRespository organiserRespository;

  @Mock
  private EntityManager entityManager;

  @Mock
  private StoredProcedureQuery storedProcedureQuery;

  @InjectMocks
  private OrganiserServiceImpl organiserService;

  /**
   * Initializes the mocks before each test case.
   */
  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  /**
   * Tests the successful retrieval of organiser details.
   */
  @Test
  void testGetOrganiserDetails_Success() throws OrganiserServiceException {
    Organiser organiser = new Organiser();
    organiser.setId(1);
    organiser.setCompanyName("Test Company");
    when(organiserRespository.findById(1)).thenReturn(Optional.of(organiser));
    OrganiserDTO result = organiserService.getOrganiserDetails(1);

    assertNotNull(result);
    assertEquals("Test Company", result.getCompanyName());
    verify(organiserRespository, times(1)).findById(1);
  }

  /**
   * Tests the failure scenario when organiser details are not found.
   */
  @Test
  void testGetOrganiserDetails_Fail() {
    when(organiserRespository.findById(1)).thenReturn(Optional.empty());
    OrganiserServiceException thrown = assertThrows(OrganiserServiceException.class, () ->
            organiserService.getOrganiserDetails(1));

    assertEquals("Organiser not found with id: 1", thrown.getMessage());
    verify(organiserRespository, times(1)).findById(1);
  }

  /**
   * Tests the successful saving of an image.
   */
  @Test
  void testSaveImage_Success() throws OrganiserServiceException {
    Organiser organiser = new Organiser();
    organiser.setId(1);
    String imageData = Base64.getEncoder().encodeToString("testImage".getBytes());
    when(organiserRespository.findById(1)).thenReturn(Optional.of(organiser));
    organiserService.saveImage(imageData, 1);

    verify(organiserRespository, times(1)).save(organiser);
  }

  /**
   * Tests the failure scenario when saving an image for a non-existent organiser.
   */
  @Test
  void testSaveImage_Fail() {
    String imageData = Base64.getEncoder().encodeToString("testImage".getBytes());
    when(organiserRespository.findById(1)).thenReturn(Optional.empty());
    OrganiserServiceException thrown = assertThrows(OrganiserServiceException.class, () ->
            organiserService.saveImage(imageData, 1));

    assertEquals("Organiser not found with id: 1", thrown.getMessage());
    verify(organiserRespository, times(1)).findById(1);
  }

  /**
   * Tests the successful retrieval of a profile picture.
   */
  @Test
  void testGetProfilePicture_Success() {
    StoredProcedureQuery query = mock(StoredProcedureQuery.class);
    byte[] expectedImage = "testImage".getBytes();
    when(entityManager.createStoredProcedureQuery("GetOrganiserProfilePicture"))
            .thenReturn(query);
    when(query.getSingleResult()).thenReturn(expectedImage);
    byte[] result = organiserService.getProfilePicture(1);

    assertNotNull(result);
    assertArrayEquals(expectedImage, result);
  }

  /**
   * Tests the successful update of organiser information.
   */
  @Test
  void testUpdateOrganiserInfo_Success() throws OrganiserServiceException {

    Organiser organiser = new Organiser(1, "Example Company", null, "12345", true,
            "123 Example Street", "1234567890", "test@example.com", "www.example.com",
            "John Doe", "Manager", "0987654321", LocalDate.of(2020, 1, 1), 50,
            "Club A, Club B", "50000", "Gold", "English", true, false);
    when(organiserRespository.findById(1)).thenReturn(Optional.of(organiser));
    OrganiserDTO organiserDTO = new OrganiserDTO();
    organiserDTO.setEmail("testupdated@example.com");
    organiserDTO.setMembershipCategory("premium");
    OrganiserDTO result = organiserService.updateOrganiserInfo(1, organiserDTO);

    assertNotNull(result);
    assertEquals("testupdated@example.com", result.getEmail());
    assertEquals("premium", result.getMembershipCategory());
    verify(organiserRespository, times(1)).save(organiser);
  }

  /**
   * Tests the failure scenario when updating information for a non-existent organiser.
   */
  @Test
  void testUpdateOrganiserInfo_Fail() {
    OrganiserDTO organiserDTO = new OrganiserDTO();
    when(organiserRespository.findById(1)).thenReturn(Optional.empty());
    OrganiserServiceException thrown = assertThrows(OrganiserServiceException.class, () ->
            organiserService.updateOrganiserInfo(1, organiserDTO));

    assertEquals("Organisation not found with id: 1", thrown.getMessage());
    verify(organiserRespository, times(1)).findById(1);
  }

  /**
   * Test for the successful approval of volunteer event.
   */
  @Test
  public void testVolunteerEventApproval_Success() throws Exception {
    int eventId = 1;
    int volunteerId = 123;
    when(entityManager.createStoredProcedureQuery("ApproveVolunteerEvent"))
            .thenReturn(storedProcedureQuery);
    when(storedProcedureQuery.getSingleResult()).thenReturn(true);
    boolean result = organiserService.volunteerEventApproval(eventId, volunteerId);
    assertTrue(result);

    verify(storedProcedureQuery).setParameter(1, volunteerId);
    verify(storedProcedureQuery).setParameter(2, eventId);
  }

  /**
   * Test for the failure scenario for approval of volunteer event.
   */
  @Test
  public void testVolunteerEventApproval_Fail() throws Exception {
    // Given
    int eventId = 1;
    int volunteerId = 123;
    when(entityManager.createStoredProcedureQuery("ApproveVolunteerEvent"))
            .thenReturn(storedProcedureQuery);
    when(storedProcedureQuery.getSingleResult())
            .thenThrow(new RuntimeException("Database error"));
    OrganiserServiceException exception = assertThrows(OrganiserServiceException.class, () -> {
      organiserService.volunteerEventApproval(eventId, volunteerId);
    });

    assertEquals("An error occurred when updating volunteer event approval table",
            exception.getMessage());
  }

}
