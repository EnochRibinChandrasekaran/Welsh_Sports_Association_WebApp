package com.example.wsa.organiser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.wsa.organiser.exception.OrganiserControllerException;
import com.example.wsa.organiser.exception.OrganiserException;
import com.example.wsa.organiser.exception.OrganiserServiceException;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

/**
 * Test class for {@link OrganiserController}.
 * Contains unit tests for the OrganiserController methods.
 */
public class OrganiserControllerTest {

  @InjectMocks
  private OrganiserController organiserController;

  @Mock
  private OrganiserService organiserService;

  /**
   * Initializes the mocks before each test case.
   */
  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  /**
   * Tests the successful retrieval of the organiser list.
   */
  @Test
  void testGetOrganiserList_Pass() {
    OrganiserDTO organiserDTO = new OrganiserDTO();
    organiserDTO.setId(1);
    when(organiserService.getOrganiserList()).thenReturn(List.of(organiserDTO));
    List<OrganiserDTO> result = organiserController.getOrganiserList();
    assertNotNull(result);
    assertEquals(1, result.size());
    verify(organiserService, times(1)).getOrganiserList();
  }

  /**
   * Tests the failure scenario when retrieving the organiser list.
   */
  @Test
  void testGetOrganiserList_Fail() throws OrganiserServiceException {
    when(organiserService.getOrganiserList())
            .thenThrow(new RuntimeException("Error Fetching Organisers"));
    RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
      organiserController.getOrganiserList();
    });
    assertEquals("Error Fetching Organisers", thrown.getMessage());
    verify(organiserService, times(1)).getOrganiserList();
  }

  /**
   * Tests the successful retrieval of an organiser by ID.
   */
  @Test
  void testGetOrganiserById_Pass() throws OrganiserException {
    OrganiserDTO organiserDTO = new OrganiserDTO();
    organiserDTO.setId(1);
    when(organiserService.getOrganiserDetails(1)).thenReturn(organiserDTO);
    OrganiserDTO result = organiserController.getOrganiserById(1);
    assertNotNull(result);
    assertEquals(1, result.getId());
    verify(organiserService, times(1)).getOrganiserDetails(1);
  }

  /**
   * Tests the failure scenario when retrieving an organiser by ID.
   */
  @Test
  void testGetOrganiserById_Fail() throws OrganiserServiceException {
    when(organiserService.getOrganiserDetails(1))
            .thenThrow(new OrganiserServiceException("Organiser not found"));
    OrganiserControllerException thrown = assertThrows(OrganiserControllerException.class, () -> {
      organiserController.getOrganiserById(1);
    });
    assertEquals("Error fetching organiser with id: 1", thrown.getMessage());
    verify(organiserService, times(1)).getOrganiserDetails(1);
  }

  /**
   * Tests the successful update of organiser information.
   */
  @Test
  void testUpdateOrganiserInfo_Pass() throws OrganiserException {
    OrganiserDTO organiserDTO = new OrganiserDTO();
    organiserDTO.setId(1);
    when(organiserService.updateOrganiserInfo(1, organiserDTO)).thenReturn(organiserDTO);
    ResponseEntity<OrganiserDTO> result = organiserController.updateOrganiserInfo(1, organiserDTO);
    assertNotNull(result);
    verify(organiserService, times(1)).updateOrganiserInfo(1, organiserDTO);
  }

  /**
   * Tests the failure scenario when updating organiser information.
   */
  @Test
  void testUpdateOrganiserInfo_Fail() throws OrganiserServiceException {
    OrganiserDTO organiserDTO = new OrganiserDTO();
    when(organiserService.updateOrganiserInfo(1, organiserDTO))
            .thenThrow(new OrganiserServiceException("Update failed"));
    OrganiserControllerException thrown = assertThrows(OrganiserControllerException.class, () -> {
      organiserController.updateOrganiserInfo(1, organiserDTO);
    });
    assertEquals("Error updating organiser info for id: 1", thrown.getMessage());
    verify(organiserService, times(1)).updateOrganiserInfo(1, organiserDTO);
  }

  /**
   * Tests the successful upload of a profile picture.
   */
  @Test
  void testUploadProfilePicture_Pass() throws OrganiserException {
    Map<String, String> payload = Map
            .of("profileImage", "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgA...");
    ResponseEntity<String> response = organiserController.uploadProfilePicture(1, payload);
    assertNotNull(response);
    verify(organiserService, times(1)).saveImage(anyString(), eq(1));
  }

  /**
   * Tests the failure scenario when uploading a profile picture.
   */
  @Test
  void testUploadProfilePicture_Fail() throws OrganiserServiceException {
    Map<String, String> payload =
            Map.of("profileImage", "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgA...");
    doThrow(new OrganiserServiceException("Upload failed"))
            .when(organiserService).saveImage(anyString(), eq(1));
    OrganiserControllerException thrown = assertThrows(OrganiserControllerException.class, () -> {
      organiserController.uploadProfilePicture(1, payload);
    });
    assertEquals("Error uploading profile picture for id: 1", thrown.getMessage());
    verify(organiserService, times(1)).saveImage(anyString(), eq(1));
  }

  /**
   * Tests the success scenario when updating volunteer approval.
   */
  @Test
  public void testVolunteerEventApproval_Success() throws Exception {
    int eventId = 1;
    String volunteerId = "123";
    when(organiserService.volunteerEventApproval(eventId, Integer.parseInt(volunteerId)))
            .thenReturn(true);
    ResponseEntity<Boolean> response = organiserController
            .volunteerEventApproval(eventId, volunteerId);

    assertEquals(true, response.getBody());
  }

  /**
   * Tests the failure scenario when updating volunteer approval.
   */
  @Test
  public void testVolunteerEventApproval_Fail() throws Exception {
    int eventId = 1;
    String volunteerId = "123";
    when(organiserService.volunteerEventApproval(eventId, Integer.parseInt(volunteerId)))
            .thenThrow(new OrganiserServiceException("Service error"));

    assertThrows(OrganiserControllerException.class, () -> {
      organiserController.volunteerEventApproval(eventId, volunteerId);
    });
  }

}
