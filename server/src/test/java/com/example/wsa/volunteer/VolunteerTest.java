package com.example.wsa.volunteer;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

/**
 * Test class for {@link Volunteer}.
 * Contains unit tests for the Volunteer model.
 */
public class VolunteerTest {
  /**
   * Tests the getter and setter methods of the Volunteer class.
   */
  @Test
  public void testVolunteerFields() {
    Volunteer volunteer = new Volunteer();

    volunteer.setId(1);
    volunteer.setFirstName("Enoch");
    volunteer.setLastName("Ribin");
    volunteer.setGender("Male");
    volunteer.setDob(LocalDate.of(1998, 10, 27));
    volunteer.setEmail("test@example.com");
    volunteer.setPhoneNumber("1234567890");
    volunteer.setAddress("123 Main St");
    volunteer.setPostalCode("12345");
    volunteer.setOccupation("Software Engineer");
    volunteer.setQualifications("BSc, MSc");
    volunteer.setAvailability("Weekdays");
    volunteer.setRoles("Developer");
    volunteer.setDbs("Yes");
    volunteer.setAccessibilityEnhancement("None");
    volunteer.setAbout("Experienced developer");
    volunteer.setRewardsEarned(5);
    volunteer.setEmergencyContactName("Friend Name");
    volunteer.setEmergencyPhoneNumber("0987654321");
    volunteer.setEmergencyRelationship("Friend");
    volunteer.setMemberStatus("Active");
    volunteer.setRating(4.5f);
    volunteer.setEventAttended(10);
    volunteer.setImage(new byte[]{1, 2, 3});
    volunteer.setMembershipLevel("Gold");

    assertEquals(1, volunteer.getId());
    assertEquals("Enoch", volunteer.getFirstName());
    assertEquals("Ribin", volunteer.getLastName());
    assertEquals("Male", volunteer.getGender());
    assertEquals(LocalDate.of(1998, 10, 27
    ), volunteer.getDob());
    assertEquals("test@example.com", volunteer.getEmail());
    assertEquals("1234567890", volunteer.getPhoneNumber());
    assertEquals("123 Main St", volunteer.getAddress());
    assertEquals("12345", volunteer.getPostalCode());
    assertEquals("Software Engineer", volunteer.getOccupation());
    assertEquals("BSc, MSc", volunteer.getQualifications());
    assertEquals("Weekdays", volunteer.getAvailability());
    assertEquals("Developer", volunteer.getRoles());
    assertEquals("Yes", volunteer.getDbs());
    assertEquals("None", volunteer.getAccessibilityEnhancement());
    assertEquals("Experienced developer", volunteer.getAbout());
    assertEquals(5, volunteer.getRewardsEarned());
    assertEquals("Friend Name", volunteer.getEmergencyContactName());
    assertEquals("0987654321", volunteer.getEmergencyPhoneNumber());
    assertEquals("Friend", volunteer.getEmergencyRelationship());
    assertEquals("Active", volunteer.getMemberStatus());
    assertEquals(4.5f, volunteer.getRating());
    assertEquals(10, volunteer.getEventAttended());
    assertArrayEquals(new byte[]{1, 2, 3}, volunteer.getImage());
    assertEquals("Gold", volunteer.getMembershipLevel());
  }

  /**
   * Tests the toString method of the Volunteer class.
   */
  @Test
  public void testVolunteerToString() {
    Volunteer volunteer = new Volunteer();
    volunteer.setFirstName("Enoch");
    volunteer.setLastName("Ribin");
    volunteer.setEmail("test@example.com");

    assertTrue(volunteer.toString().contains("Enoch"));
    assertTrue(volunteer.toString().contains("Ribin"));
    assertTrue(volunteer.toString().contains("test@example.com"));
  }


  /**
   * Tests the equality and hashCode methods of the Volunteer class.
   */
  @Test
  public void testVolunteerEqualsAndHashCode() {
    Volunteer volunteer1 = new Volunteer();
    volunteer1.setId(1);
    volunteer1.setFirstName("Enoch");

    Volunteer volunteer2 = new Volunteer();
    volunteer2.setId(1);
    volunteer2.setFirstName("Enoch");

    assertEquals(volunteer1, volunteer2);
    assertEquals(volunteer1.hashCode(), volunteer2.hashCode());
  }
}
