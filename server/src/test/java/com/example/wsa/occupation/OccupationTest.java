package com.example.wsa.occupation;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the {@link Occupation} class.
 */
public class OccupationTest {

  /**
   * Tests the getters and setters of the {@link Occupation} class.
   */
  @Test
  void testOccupationModel() {
    Occupation occupation = new Occupation();
    occupation.setId(1);
    occupation.setName("Software Engineer");
    occupation.setCategory("IT");

    assertThat(occupation.getId()).isEqualTo(1);
    assertThat(occupation.getName()).isEqualTo("Software Engineer");
    assertThat(occupation.getCategory()).isEqualTo("IT");

    occupation.setName("Data Scientist");
    occupation.setCategory("Data");

    assertThat(occupation.getName()).isEqualTo("Data Scientist");
    assertThat(occupation.getCategory()).isEqualTo("Data");

    String expectedString = "Occupation(id=1, name=Data Scientist, category=Data)";
    assertThat(occupation.toString()).isEqualTo(expectedString);
  }

  /**
   * Tests the equality and hash code methods of the {@link Occupation} class.
   */
  @Test
  void testOccupationEquality() {
    Occupation occupation1 = new Occupation(1, "Software Engineer", "IT");
    Occupation occupation2 = new Occupation(1, "Software Engineer", "IT");

    assertThat(occupation1).isEqualTo(occupation2);
    assertThat(occupation1.hashCode()).isEqualTo(occupation2.hashCode());

    occupation2.setName("Data Scientist");
    assertThat(occupation1).isNotEqualTo(occupation2);
  }

  /**
   * Tests the default constructor of the {@link Occupation} class.
   */
  @Test
  void testOccupationDefaultConstructor() {
    Occupation occupation = new Occupation();

    assertThat(occupation.getId()).isNull();
    assertThat(occupation.getName()).isNull();
    assertThat(occupation.getCategory()).isNull();
  }
}
