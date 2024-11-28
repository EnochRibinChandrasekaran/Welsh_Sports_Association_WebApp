package com.example.wsa.qualification;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * Test class for {@link Qualification}.
 * Contains unit tests for the Qualification model.
 */
public class QualificationTest {

  /**
   * Tests the getter and setter methods of the Qualification class.
   */
  @Test
  void testQualificationModel() {
    Qualification qualification = new Qualification();
    qualification.setId(1);
    qualification.setName("Computer Science");
    qualification.setType(QualificationType.EDUCATIONAL);

    assertThat(qualification.getId()).isEqualTo(1);
    assertThat(qualification.getName()).isEqualTo("Computer Science");
    assertThat(qualification.getType()).isEqualTo(QualificationType.EDUCATIONAL);

    qualification.setName("Data Science");
    qualification.setType(QualificationType.PROFESSIONAL_CERTIFICATION);

    assertThat(qualification.getName()).isEqualTo("Data Science");
    assertThat(qualification.getType()).isEqualTo(QualificationType.PROFESSIONAL_CERTIFICATION);

    String expectedString =
            "Qualification(id=1, name=Data Science, type=PROFESSIONAL_CERTIFICATION)";
    assertThat(qualification.toString()).isEqualTo(expectedString);
  }

  /**
   * Tests the equality and hashCode methods of the Qualification class.
   */
  @Test
  void testQualificationEquality() {
    Qualification qualification1 =
            new Qualification(1, "Computer Science", QualificationType.EDUCATIONAL);
    Qualification qualification2 =
            new Qualification(1, "Computer Science", QualificationType.EDUCATIONAL);

    assertThat(qualification1).isEqualTo(qualification2);
    assertThat(qualification1.hashCode()).isEqualTo(qualification2.hashCode());

    qualification2.setName("Data Science");
    assertThat(qualification1).isNotEqualTo(qualification2);
  }

  /**
   * Tests the default constructor of the Qualification class.
   */
  @Test
  void testQualificationDefaultConstructor() {
    Qualification qualification = new Qualification();

    assertThat(qualification.getId()).isNull();
    assertThat(qualification.getName()).isNull();
    assertThat(qualification.getType()).isNull();
  }
}
