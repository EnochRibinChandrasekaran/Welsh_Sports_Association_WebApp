package com.example.wsa.turnover;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * Test class for {@link Turnover}.
 * Contains unit tests for the Turnover model.
 */
public class TurnoverTest {

  /**
   * Tests the getter and setter methods of the Turnover class.
   */
  @Test
  void testTurnoverModel() {
    Turnover turnover = new Turnover();
    turnover.setId(1);
    turnover.setTypes("High Turnover");
    assertThat(turnover.getId()).isEqualTo(1);
    assertThat(turnover.getTypes()).isEqualTo("High Turnover");

    turnover.setTypes("Low Turnover");
    assertThat(turnover.getTypes()).isEqualTo("Low Turnover");

    String expectedString = "Turnover(id=1, types=Low Turnover)";
    assertThat(turnover.toString()).isEqualTo(expectedString);
  }

  /**
   * Tests the equality and hashCode methods of the Turnover class.
   */
  @Test
  void testTurnoverEquality() {
    Turnover turnover1 = new Turnover(1, "Medium Turnover");
    Turnover turnover2 = new Turnover(1, "Medium Turnover");
    assertThat(turnover1).isEqualTo(turnover2);
    assertThat(turnover1.hashCode()).isEqualTo(turnover2.hashCode());

    turnover2.setTypes("Low Turnover");
    assertThat(turnover1).isNotEqualTo(turnover2);
  }

  /**
   * Tests the default constructor of the Turnover class.
   */
  @Test
  void testTurnoverDefaultConstructor() {
    Turnover turnover = new Turnover();
    assertThat(turnover.getId()).isNull();
    assertThat(turnover.getTypes()).isNull();
  }
}
