package com.example.wsa.volunteer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import com.example.wsa.event.EventDTO;
import com.example.wsa.utility.NotNullResponse;
import com.example.wsa.volunteer.exception.VolunteerControllerException;
import com.example.wsa.volunteer.exception.VolunteerServiceException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Test class for {@link VolunteerController}.
 * Contains unit tests for the VolunteerController methods.
 */
public class VolunteerControllerTest {

  @Mock
  private VolunteerService volunteerService;

  @InjectMocks
  private VolunteerController volunteerController;

  /**
   * Initializes mocks before each test case.
   */
  public VolunteerControllerTest() {
    initMocks(this);
  }

  /**
   * Tests the successful retrieval of all upcoming events.
   */
  @Test
  public void testUpcomingEvents_Pass() {
    List<EventDTO> eventList = List.of(new EventDTO(1, "Event 1", LocalDate.now()));
    when(volunteerService.upcomingEvents()).thenReturn(eventList);

    List<NotNullResponse<EventDTO>> response = volunteerController.upcomingEvents();

    assertEquals(1, response.size());
    assertEquals("Event 1", response.get(0).getNonNullFields().get("name"));
  }

  /**
   * Tests the failure scenario for Upcoming Events.
   */
  @Test
  public void testUpcomingEvents_Fail_EmptyList() {
    when(volunteerService.upcomingEvents()).thenReturn(Collections.emptyList());

    List<NotNullResponse<EventDTO>> response = volunteerController.upcomingEvents();

    assertTrue(response.isEmpty());
  }

  /**
   * Tests the approved events dates.
   */
  @Test
  public void testApprovedEventsDates_Pass() throws Exception {
    List<EventDTO> eventList = List.of(new EventDTO(1, "Approved Event", LocalDate.now()));
    when(volunteerService.approvedEventsDates()).thenReturn(eventList);

    List<NotNullResponse<EventDTO>> response = volunteerController.approvedEventsDates();

    assertEquals(1, response.size());
    assertEquals("Approved Event", response.get(0).getNonNullFields().get("name"));
  }

  /**
   * Tests the failure scenario approved events dates.
   */
  @Test
  public void testApprovedEventsDates_Fail_Exception() throws Exception {
    when(volunteerService.approvedEventsDates())
            .thenThrow(new VolunteerServiceException("Service error"));
    assertThrows(VolunteerControllerException.class, () -> {
      volunteerController.approvedEventsDates();
    });
  }


  /**
   * Tests the success scenario get volunteer details.
   */
  @Test
  public void testGetVolunteer_Pass() throws Exception {
    VolunteerDTO volunteerDTO = new VolunteerDTO();
    volunteerDTO.setId(1);
    volunteerDTO.setFirstName("Enoch");
    volunteerDTO.setLastName("Ribin");
    volunteerDTO.setGender("Male");
    volunteerDTO.setDob(LocalDate.of(1998, 10, 27));
    volunteerDTO.setEmail("testEmail@gmail.com");
    when(volunteerService.getVolunteerDetails(1)).thenReturn(volunteerDTO);

    NotNullResponse<VolunteerDTO> response = volunteerController.getVolunteer(1);

    assertEquals("Enoch", response.getNonNullFields().get("firstName"));
  }

  /**
   * Tests the failure scenario get volunteer details.
   */
  @Test
  public void testGetVolunteer_Fail_NotFound() throws Exception {
    when(volunteerService.getVolunteerDetails(1))
            .thenThrow(new VolunteerServiceException("Not Found"));

    assertThrows(VolunteerControllerException.class, () -> {
      volunteerController.getVolunteer(1);
    });
  }

  /**
   * Tests the success scenario update volunteer details.
   */
  @Test
  public void testUpdateVolunteerInfo_Pass() throws Exception {
    VolunteerDTO volunteerDTO = new VolunteerDTO();
    volunteerDTO.setId(1);
    volunteerDTO.setFirstName("Enoch");
    volunteerDTO.setLastName("Ribin");
    volunteerDTO.setGender("Male");
    volunteerDTO.setDob(LocalDate.of(1998, 10, 15));
    volunteerDTO.setEmail("testEmail@gmail.com");

    when(volunteerService.updateVolunteerInfo(
            eq(1), any(VolunteerDTO.class))).thenReturn(volunteerDTO);

    ResponseEntity<VolunteerDTO> response = volunteerController
            .updateVolunteerInfo(1, volunteerDTO);

    assertEquals("Enoch", response.getBody().getFirstName());
  }

  /**
   * Tests the failure scenario update volunteer details.
   */
  @Test
  public void testUpdateVolunteerInfo_Fail_Exception() throws Exception {
    when(volunteerService.updateVolunteerInfo(eq(1), any(VolunteerDTO.class)))
            .thenThrow(new VolunteerServiceException("Update failed"));

    assertThrows(VolunteerControllerException.class, () -> {
      volunteerController.updateVolunteerInfo(1, new VolunteerDTO());
    });
  }

  /**
   * Tests success scenario of the upload image.
   */
  @Test
  public void testUploadProfilePicture_Pass() throws Exception {
    Map<String, String> payload = Map
            .of("profileImage",
                    "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAA...");

    ResponseEntity<String> response = volunteerController
            .uploadProfilePicture(1, payload);

    assertEquals("Image uploaded", response.getBody());
  }

  /**
   * Tests failure scenario the upload image.
   */
  @Test
  public void testUploadProfilePicture_Fail_Exception() throws Exception {
    Map<String, String> payload = Map
            .of("profileImage",
                    "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAA...");

    doThrow(new VolunteerServiceException("Save failed"))
            .when(volunteerService).saveImage(anyString(), eq(1));

    assertThrows(VolunteerControllerException.class, () -> {
      volunteerController.uploadProfilePicture(1, payload);
    });
  }

  /**
   * Tests the success scenario to get image.
   */
  @Test
  public void testGetProfilePicture_Pass() throws Exception {
    byte[] image = new byte[]{0x1, 0x2, 0x3};
    when(volunteerService.getProfilePicture(1)).thenReturn(image);

    ResponseEntity<byte[]> response = volunteerController.getProfilePicture(1);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(image, response.getBody());
  }

  /**
   * Tests the failure scenario to get image.
   */
  @Test
  public void testGetProfilePicture_Fail_Exception() throws Exception {
    when(volunteerService
            .getProfilePicture(1))
            .thenThrow(new VolunteerServiceException("Not Found"));

    assertThrows(VolunteerControllerException.class, () -> {
      volunteerController.getProfilePicture(1);
    });
  }

  /**
   * Tests the success scenario to get volunteer list.
   */
  @Test
  public void testGetVolunteers_Pass() throws Exception {
    VolunteerDTO volunteerDTO1 = new VolunteerDTO();
    volunteerDTO1.setId(1);
    volunteerDTO1.setFirstName("Enoch");
    volunteerDTO1.setLastName("Ribin");
    VolunteerDTO volunteerDTO2 = new VolunteerDTO();
    volunteerDTO2.setId(2);
    volunteerDTO2.setFirstName("Enoch");
    volunteerDTO2.setLastName("Ribin");
    Page<VolunteerDTO> volunteerPage = new PageImpl<>(
            List.of(volunteerDTO1, volunteerDTO2));
    Pageable pageable = PageRequest.of(0, 10);
    when(volunteerService.getAllVolunteers(pageable)).thenReturn(volunteerPage);

    ResponseEntity<Page<VolunteerDTO>> response = volunteerController
            .getVolunteers(0, 10);

    assertEquals(2, response.getBody().getTotalElements());
  }

  /**
   * Tests the failure scenario to get volunteer list.
   */
  @Test
  public void testGetVolunteers_Fail_Exception() throws Exception {
    Pageable pageable = PageRequest.of(0, 10);
    when(volunteerService.getAllVolunteers(pageable))
            .thenThrow(new VolunteerServiceException("Error"));

    assertThrows(VolunteerControllerException.class, () -> {
      volunteerController.getVolunteers(0, 10);
    });
  }
}
