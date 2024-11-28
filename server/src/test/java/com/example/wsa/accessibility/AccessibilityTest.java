package com.example.wsa.accessibility;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.wsa.accessibilty.Accessibility;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the {@link Accessibility} model class.
 */
public class AccessibilityTest {

  /**
   * Tests the getters, setters, and toString method of the Accessibility class.
   */
  @Test
  void testAccessibilityModel() {
    Accessibility accessibility = new Accessibility();
    accessibility.setId(1);
    accessibility.setName("Wheelchair Ramp");

    assertThat(accessibility.getId()).isEqualTo(1);
    assertThat(accessibility.getName()).isEqualTo("Wheelchair Ramp");

    accessibility.setName("Accessible Parking");
    assertThat(accessibility.getName()).isEqualTo("Accessible Parking");

    String expectedString = "Accessibility(id=1, name=Accessible Parking)";
    assertThat(accessibility.toString()).isEqualTo(expectedString);
  }

  /**
   * Tests the equality and hashCode methods of the Accessibility class.
   */
  @Test
  void testAccessibilityEquality() {
    Accessibility accessibility1 = new Accessibility(1, "Braille Signage");
    Accessibility accessibility2 = new Accessibility(1, "Braille Signage");

    assertThat(accessibility1).isEqualTo(accessibility2);
    assertThat(accessibility1.hashCode()).isEqualTo(accessibility2.hashCode());

    accessibility2.setName("Ramp Access");
    assertThat(accessibility1).isNotEqualTo(accessibility2);
  }

  /**
   * Tests the default constructor of the Accessibility class.
   */
  @Test
  void testAccessibilityDefaultConstructor() {
    Accessibility accessibility = new Accessibility();

    assertThat(accessibility.getId()).isNull();
    assertThat(accessibility.getName()).isNull();
  }
}
