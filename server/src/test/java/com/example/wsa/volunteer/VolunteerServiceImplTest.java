package com.example.wsa.volunteer;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.wsa.event.EventDTO;
import com.example.wsa.event.EventRepository;
import com.example.wsa.volunteer.exception.VolunteerServiceException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.StoredProcedureQuery;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

/**
 * Test class for {@link VolunteerServiceImpl}.
 * Contains unit tests for the VolunteerServiceImpl methods.
 */
public class VolunteerServiceImplTest {

  @Mock
  private VolunteerRepository volunteerRepository;

  @Mock
  private EventRepository eventRepository;

  @Mock
  private EntityManager entityManager;

  @InjectMocks
  private VolunteerServiceImpl volunteerService;

  /**
   * Initializes the mocks before each test case.
   */
  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testUpcomingEvents_Pass() {
    List<Object[]> mockResults = List
            .of(new Object[]{1, "Event 1", new byte[]{1, 2, 3}, "12345", "City", Date
                            .valueOf(LocalDate.now()), "Monday"},
                    new Object[]{2, "Event 2", new byte[]{4, 5, 6}, "67890", "Another City",
                            Date.valueOf(LocalDate.now().plusDays(1)), "Tuesday"});

    when(eventRepository.findUpcomingEvents()).thenReturn(mockResults);

    List<EventDTO> result = volunteerService.upcomingEvents();

    // Assuming your service expects two results, not one
    assertEquals(2, result.size());
    assertEquals("Event 1", result.get(0).getName());
    assertEquals("Event 2", result.get(1).getName());
  }


  @Test
  public void testUpcomingEvents_Fail() {
    when(eventRepository.findUpcomingEvents()).thenReturn(Collections.emptyList());

    List<EventDTO> result = volunteerService.upcomingEvents();

    assertTrue(result.isEmpty());
  }

  // Test case for approvedEventsDates()
  @Test
  public void testApprovedEventsDates_Pass() throws VolunteerServiceException {
    List<Object[]> mockResults = List.of(
            new Object[]{1, "Event 1", Date.valueOf(LocalDate.now())},
            new Object[]{2, "Event 2", Date.valueOf(LocalDate.now().plusDays(1))}
    );

    when(eventRepository.findApprovedEventsDates()).thenReturn(mockResults);

    List<EventDTO> result = volunteerService.approvedEventsDates();

    // Verify that the results match the expected values
    assertEquals(2, result.size());
    assertEquals("Event 1", result.get(0).getName());
    assertEquals("Event 2", result.get(1).getName());
  }


  @Test
  public void testApprovedEventsDates_Fail() {
    when(eventRepository.findApprovedEventsDates())
            .thenThrow(new RuntimeException("Error fetching Events in the Repository"));

    assertThrows(VolunteerServiceException.class, () -> {
      volunteerService.approvedEventsDates();
    });
  }

  @Test
  public void testGetVolunteerDetails_Pass() throws VolunteerServiceException {
    Volunteer volunteer = new Volunteer();
    volunteer.setId(1);
    volunteer.setFirstName("Enoch");
    volunteer.setLastName("Ribin");
    volunteer.setAvailability("Master's,PhD");
    volunteer.setQualifications("Available");
    volunteer.setRoles("Coordinator,Assistant");
    volunteer.setAccessibilityEnhancement("Wheelchair,Ramp");
    when(volunteerRepository.findById(1)).thenReturn(Optional.of(volunteer));

    VolunteerDTO result = volunteerService.getVolunteerDetails(1);

    assertEquals("Enoch", result.getFirstName());
    assertEquals(
            "Master's,PhD",
            String.join(",", result.getAvailability()));
    assertEquals("Available",
            String.join(",", result.getQualifications()));
    assertEquals("Coordinator,Assistant",
            String.join(",", result.getRoles()));
    assertEquals("Wheelchair,Ramp",
            String.join(",", result.getAccessibilityEnhancement()));
  }


  @Test
  public void testGetVolunteerDetails_Fail() throws VolunteerServiceException {
    when(volunteerRepository.findById(1)).thenThrow(new RuntimeException("Volunteer not found"));

    assertThrows(VolunteerServiceException.class, () -> {
      volunteerService.getVolunteerDetails(1);
    });
  }

  @Test
  public void testUpdateVolunteerInfo_Pass() throws VolunteerServiceException {
    Volunteer volunteer = new Volunteer();
    volunteer.setId(1);
    when(volunteerRepository.findById(1)).thenReturn(Optional.of(volunteer));

    VolunteerDTO volunteerDTO = new VolunteerDTO();
    volunteerDTO.setFirstName("Enoch");
    volunteerDTO.setLastName("Ribin");
    volunteerDTO.setAvailability(Arrays.asList("Available"));
    volunteerDTO.setQualifications(Arrays.asList("Master's", "PhD"));
    volunteerDTO.setRoles(Arrays.asList("Coordinator", "Assistant"));
    volunteerDTO.setAccessibilityEnhancement(Arrays.asList("Wheelchair", "Ramp"));

    when(volunteerRepository.save(any(Volunteer.class))).thenReturn(volunteer);

    VolunteerDTO updatedVolunteer = volunteerService.updateVolunteerInfo(1, volunteerDTO);

    assertEquals("Enoch", updatedVolunteer.getFirstName());
  }

  @Test
  public void testUpdateVolunteerInfo_Fail() throws VolunteerServiceException {
    when(volunteerRepository.findById(1)).thenThrow(new RuntimeException("Volunteer not found"));

    VolunteerDTO volunteerDTO = new VolunteerDTO();
    assertThrows(VolunteerServiceException.class, () -> {
      volunteerService.updateVolunteerInfo(1, volunteerDTO);
    });
  }

  @Test
  public void testSaveImage_Pass() throws VolunteerServiceException {
    Volunteer volunteer = new Volunteer();
    volunteer.setId(1);
    when(volunteerRepository.findById(1)).thenReturn(Optional.of(volunteer));

    String base64Image = "iVBORw0KGgoAAAANSUhEUgAAAAUA"
            + "AAAFCAYAAACNbyblAAAAHElEQVQI12P4"
            + "//8/w38GIAXDIBKE0DHxgljNBAAO9TXL0Y4OHwAAAABJRU5ErkJggg==";

    volunteerService.saveImage(base64Image, 1);

    verify(volunteerRepository).save(any(Volunteer.class));
  }

  @Test
  public void testSaveImage_Fail() throws VolunteerServiceException {
    when(volunteerRepository.findById(1)).thenThrow(new RuntimeException("Volunteer not found"));

    assertThrows(VolunteerServiceException.class, () -> {
      volunteerService.saveImage("invalid_base64", 1);
    });
  }

  @Test
  public void testGetProfilePicture_Pass() throws VolunteerServiceException {
    byte[] mockImage = new byte[]{1, 2, 3};
    StoredProcedureQuery query = mock(StoredProcedureQuery.class);
    when(entityManager.createStoredProcedureQuery("GetVolunteerProfilePicture")).thenReturn(query);
    when(query.getSingleResult()).thenReturn(mockImage);

    byte[] result = volunteerService.getProfilePicture(1);

    assertArrayEquals(mockImage, result);
  }

  @Test
  public void testGetProfilePicture_Fail() throws VolunteerServiceException {
    StoredProcedureQuery query = mock(StoredProcedureQuery.class);
    EntityManager entityManager1 = mock(EntityManager.class);
    when(entityManager1.createStoredProcedureQuery("GetVolunteerProfilePicture")).thenReturn(query);
    when(query.getSingleResult()).thenThrow(new RuntimeException("Error Fetching Image"));

    assertThrows(VolunteerServiceException.class, () -> {
      volunteerService.getProfilePicture(1);
    });
  }

  @Test
  public void testGetAllVolunteers_Fail() throws VolunteerServiceException {
    Pageable pageable = PageRequest.of(0, 10);
    StoredProcedureQuery query = mock(StoredProcedureQuery.class);
    when(entityManager
            .createStoredProcedureQuery("getPageVolunteer", Volunteer.class))
            .thenReturn(query);
    when(query.getResultList()).thenThrow(new RuntimeException("Error Fetching Volunteers"));

    assertThrows(VolunteerServiceException.class, () -> {
      volunteerService.getAllVolunteers(pageable);
    });
  }

}
