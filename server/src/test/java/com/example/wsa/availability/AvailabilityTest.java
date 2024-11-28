package com.example.wsa.availability;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the {@link Availability} model class.
 */
public class AvailabilityTest {

  /**
   * Tests the getters, setters, and toString method of the Availability class.
   */
  @Test
  void testAvailabilityModel() {
    Availability availability = new Availability();
    availability.setId(1);
    availability.setAvailableDays("Monday, Wednesday, Friday");

    assertThat(availability.getId()).isEqualTo(1);
    assertThat(availability.getAvailableDays()).isEqualTo("Monday, Wednesday, Friday");

    availability.setAvailableDays("Tuesday, Thursday");
    assertThat(availability.getAvailableDays()).isEqualTo("Tuesday, Thursday");

    String expectedString = "Availability(id=1, availableDays=Tuesday, Thursday)";
    assertThat(availability.toString()).isEqualTo(expectedString);
  }

  /**
   * Tests the equality and hashCode methods of the Availability class.
   */
  @Test
  void testAvailabilityEquality() {
    Availability availability1 = new Availability(1, "Monday, Wednesday, Friday");
    Availability availability2 = new Availability(1, "Monday, Wednesday, Friday");

    assertThat(availability1).isEqualTo(availability2);
    assertThat(availability1.hashCode()).isEqualTo(availability2.hashCode());

    availability2.setAvailableDays("Tuesday, Thursday");
    assertThat(availability1).isNotEqualTo(availability2);
  }

  /**
   * Tests the default constructor of the Availability class.
   */
  @Test
  void testAvailabilityDefaultConstructor() {
    Availability availability = new Availability();

    assertThat(availability.getId()).isNull();
    assertThat(availability.getAvailableDays()).isNull();
  }
}
