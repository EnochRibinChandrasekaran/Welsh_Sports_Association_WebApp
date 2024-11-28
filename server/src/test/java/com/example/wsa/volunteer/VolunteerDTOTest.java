package com.example.wsa.volunteer;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Test class for {@link VolunteerDTO}.
 * Contains unit tests for the VolunteerDTO model.
 */
public class VolunteerDTOTest {

  /**
   * Tests the getter and setter methods of the VolunteerDTO class.
   */
  @Test
  public void testVolunteerDTOFields() {
    VolunteerDTO volunteerDTO = new VolunteerDTO();

    volunteerDTO.setId(1);
    volunteerDTO.setFirstName("Enoch");
    volunteerDTO.setLastName("Ribin");
    volunteerDTO.setGender("Male");
    volunteerDTO.setDob(LocalDate.of(1998, 10, 27));
    volunteerDTO.setEmail("test@gmail.com");
    volunteerDTO.setPhoneNumber("9876543210");
    volunteerDTO.setAddress("456 Elm St");
    volunteerDTO.setPostalCode("54321");
    volunteerDTO.setOccupation("Software Engineer");
    List<String> qualifications = Arrays.asList("BSc", "MSc");
    volunteerDTO.setQualifications(qualifications);
    List<String> availability = Arrays.asList("Weekends", "Evenings");
    volunteerDTO.setAvailability(availability);
    List<String> roles = Arrays.asList("Developer", "Manager");
    volunteerDTO.setRoles(roles);
    volunteerDTO.setDbs("Yes");
    List<String> accessibilityEnhancement = Arrays.asList("Wheelchair", "Sign Language");
    volunteerDTO.setAccessibilityEnhancement(accessibilityEnhancement);
    volunteerDTO.setAbout("Experienced Software Engineer");
    volunteerDTO.setRewardsEarned(8);
    volunteerDTO.setEmergencyContactName("Friend Name");
    volunteerDTO.setEmergencyPhoneNumber("1234567890");
    volunteerDTO.setEmergencyRelationship("Friend");
    volunteerDTO.setMemberStatus("Active");
    volunteerDTO.setRating(4.8f);
    volunteerDTO.setEventAttended(20);
    volunteerDTO.setMembership("Platinum");
    volunteerDTO.setImage(new byte[]{4, 5, 6});

    assertEquals(1, volunteerDTO.getId());
    assertEquals("Enoch", volunteerDTO.getFirstName());
    assertEquals("Ribin", volunteerDTO.getLastName());
    assertEquals("Male", volunteerDTO.getGender());
    assertEquals(LocalDate.of(1998, 10, 27), volunteerDTO.getDob());
    assertEquals("test@gmail.com", volunteerDTO.getEmail());
    assertEquals("9876543210", volunteerDTO.getPhoneNumber());
    assertEquals("456 Elm St", volunteerDTO.getAddress());
    assertEquals("54321", volunteerDTO.getPostalCode());
    assertEquals("Software Engineer", volunteerDTO.getOccupation());
    assertEquals(qualifications, volunteerDTO.getQualifications());
    assertEquals(availability, volunteerDTO.getAvailability());
    assertEquals(roles, volunteerDTO.getRoles());
    assertEquals("Yes", volunteerDTO.getDbs());
    assertEquals(accessibilityEnhancement, volunteerDTO.getAccessibilityEnhancement());
    assertEquals("Experienced Software Engineer", volunteerDTO.getAbout());
    assertEquals(8, volunteerDTO.getRewardsEarned());
    assertEquals("Friend Name", volunteerDTO.getEmergencyContactName());
    assertEquals("1234567890", volunteerDTO.getEmergencyPhoneNumber());
    assertEquals("Friend", volunteerDTO.getEmergencyRelationship());
    assertEquals("Active", volunteerDTO.getMemberStatus());
    assertEquals(4.8f, volunteerDTO.getRating());
    assertEquals(20, volunteerDTO.getEventAttended());
    assertEquals("Platinum", volunteerDTO.getMembership());
    assertArrayEquals(new byte[]{4, 5, 6}, volunteerDTO.getImage());
  }

  /**
   * Tests the toString method of the VolunteerDTO class.
   */
  @Test
  public void testVolunteerDTOToString() {
    VolunteerDTO volunteerDTO = new VolunteerDTO();
    volunteerDTO.setFirstName("Enoch");
    volunteerDTO.setLastName("Ribin");
    volunteerDTO.setEmail("test@gmail.com");

    assertTrue(volunteerDTO.toString().contains("Enoch"));
    assertTrue(volunteerDTO.toString().contains("Ribin"));
    assertTrue(volunteerDTO.toString().contains("test@gmail.com"));
  }

  /**
   * Tests the equality and hashCode methods of the VolunteerDTO class.
   */
  @Test
  public void testVolunteerDTOEqualsAndHashCode() {
    VolunteerDTO volunteerDTO1 = new VolunteerDTO();
    volunteerDTO1.setId(1);
    volunteerDTO1.setFirstName("Enoch");

    VolunteerDTO volunteerDTO2 = new VolunteerDTO();
    volunteerDTO2.setId(1);
    volunteerDTO2.setFirstName("Enoch");

    assertEquals(volunteerDTO1, volunteerDTO2);
    assertEquals(volunteerDTO1.hashCode(), volunteerDTO2.hashCode());
  }
}
