package com.example.wsa.organiser;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

/**
 * Test class for {@link Organiser}.
 * Contains unit tests for the Organiser model.
 */
public class OrganiserTest {

  /**
   * Tests the getter and setter methods of the Organiser class.
   */
  @Test
  void testOrganiserModel() {
    Organiser organiser = new Organiser();
    organiser.setId(1);
    organiser.setCompanyName("Example Company");
    organiser.setLogo(new byte[]{1, 2, 3});
    organiser.setPostCode("12345");
    organiser.setCharity(true);
    organiser.setAddress("123 Example Street");
    organiser.setTelephone("1234567890");
    organiser.setEmail("test@example.com");
    organiser.setWebsite("www.example.com");
    organiser.setMainContactName("John Doe");
    organiser.setMainContactPosition("Manager");
    organiser.setMainContactMobileNumber("0987654321");
    organiser.setFoundingDate(LocalDate.of(2020, 1, 1));
    organiser.setNumberOfMembers(50);
    organiser.setAssociatedClubs("Club A, Club B");
    organiser.setTurnover("50000");
    organiser.setMembershipCategory("Gold");
    organiser.setPreferredLanguage("English");
    organiser.setNewsletter(true);
    organiser.setActivated(false);

    assertThat(organiser.getId()).isEqualTo(1);
    assertThat(organiser.getCompanyName()).isEqualTo("Example Company");
    assertThat(organiser.getLogo()).isEqualTo(new byte[]{1, 2, 3});
    assertThat(organiser.getPostCode()).isEqualTo("12345");
    assertThat(organiser.getCharity()).isTrue();
    assertThat(organiser.getAddress()).isEqualTo("123 Example Street");
    assertThat(organiser.getTelephone()).isEqualTo("1234567890");
    assertThat(organiser.getEmail()).isEqualTo("test@example.com");
    assertThat(organiser.getWebsite()).isEqualTo("www.example.com");
    assertThat(organiser.getMainContactName()).isEqualTo("John Doe");
    assertThat(organiser.getMainContactPosition()).isEqualTo("Manager");
    assertThat(organiser.getMainContactMobileNumber()).isEqualTo("0987654321");
    assertThat(organiser.getFoundingDate()).isEqualTo(LocalDate.of(2020, 1, 1));
    assertThat(organiser.getNumberOfMembers()).isEqualTo(50);
    assertThat(organiser.getAssociatedClubs()).isEqualTo("Club A, Club B");
    assertThat(organiser.getTurnover()).isEqualTo("50000");
    assertThat(organiser.getMembershipCategory()).isEqualTo("Gold");
    assertThat(organiser.getPreferredLanguage()).isEqualTo("English");
    assertThat(organiser.getNewsletter()).isTrue();
    assertThat(organiser.getActivated()).isFalse();

    organiser.setCompanyName("New Company Name");
    assertThat(organiser.getCompanyName()).isEqualTo("New Company Name");
  }

  /**
   * Tests the equality and hashCode methods of the Organiser class.
   */
  @Test
  void testOrganiserEquality() {
    Organiser organiser1 = new Organiser(1, "Example Company", null, "12345", true,
            "123 Example Street", "1234567890", "test@example.com", "www.example.com",
            "John Doe", "Manager", "0987654321", LocalDate.of(2020, 1, 1), 50,
            "Club A, Club B", "50000", "Gold", "English", true, false);

    Organiser organiser2 = new Organiser(1, "Example Company", null, "12345", true,
            "123 Example Street", "1234567890", "test@example.com", "www.example.com",
            "John Doe", "Manager", "0987654321", LocalDate.of(2020, 1, 1), 50,
            "Club A, Club B", "50000", "Gold", "English", true, false);

    assertThat(organiser1).isEqualTo(organiser2);
    assertThat(organiser1.hashCode()).isEqualTo(organiser2.hashCode());

    organiser2.setCompanyName("Different Company");
    assertThat(organiser1).isNotEqualTo(organiser2);
  }

  /**
   * Tests the toString method of the Organiser class.
   */
  @Test
  void testOrganiserToString() {
    Organiser organiser = new Organiser(1, "Example Company", null, "12345", true,
            "123 Example Street", "1234567890", "test@example.com", "www.example.com",
            "John Doe", "Manager", "0987654321", LocalDate.of(2020, 1, 1), 50,
            "Club A, Club B", "50000", "Gold", "English", true, false);
    String expectedToString = "Organiser(id=1, companyName=Example Company,"
            + " logo=null, postCode=12345,"
            + " charity=true, address=123 Example Street,"
            + " telephone=1234567890, email=test@example.com, "
            + "website=www.example.com, mainContactName=John Doe, mainContactPosition=Manager, "
            + "mainContactMobileNumber=0987654321, foundingDate=2020-01-01, numberOfMembers=50, "
            + "associatedClubs=Club A, Club B, turnover=50000, membershipCategory=Gold, "
            + "preferredLanguage=English, newsletter=true, activated=false)";

    assertThat(organiser.toString()).isEqualTo(expectedToString);
  }

  /**
   * Tests the default constructor of the Organiser class.
   */
  @Test
  void testOrganiserDefaultConstructor() {
    Organiser organiser = new Organiser();

    assertThat(organiser.getId()).isNull();
    assertThat(organiser.getCompanyName()).isNull();
    assertThat(organiser.getLogo()).isNull();
    assertThat(organiser.getPostCode()).isNull();
    assertThat(organiser.getCharity()).isNull();
    assertThat(organiser.getAddress()).isNull();
    assertThat(organiser.getTelephone()).isNull();
    assertThat(organiser.getEmail()).isNull();
    assertThat(organiser.getWebsite()).isNull();
    assertThat(organiser.getMainContactName()).isNull();
    assertThat(organiser.getMainContactPosition()).isNull();
    assertThat(organiser.getMainContactMobileNumber()).isNull();
    assertThat(organiser.getFoundingDate()).isNull();
    assertThat(organiser.getNumberOfMembers()).isNull();
    assertThat(organiser.getAssociatedClubs()).isNull();
    assertThat(organiser.getTurnover()).isNull();
    assertThat(organiser.getMembershipCategory()).isNull();
    assertThat(organiser.getPreferredLanguage()).isNull();
    assertThat(organiser.getNewsletter()).isNull();
    assertThat(organiser.getActivated()).isNull();
  }
}
