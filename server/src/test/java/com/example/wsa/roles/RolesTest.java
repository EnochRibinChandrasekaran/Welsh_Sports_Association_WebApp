package com.example.wsa.roles;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * Test class for {@link Roles}.
 * Contains unit tests for the Roles model.
 */
public class RolesTest {

  /**
   * Tests the getter and setter methods of the Roles class.
   */
  @Test
  void testRolesModel() {
    Roles role = new Roles();
    role.setId(1);
    role.setTitle("Admin");
    assertThat(role.getId()).isEqualTo(1);
    assertThat(role.getTitle()).isEqualTo("Admin");

    role.setTitle("User");
    assertThat(role.getTitle()).isEqualTo("User");

    String expectedString = "Roles(id=1, title=User)";
    assertThat(role.toString()).isEqualTo(expectedString);
  }

  /**
   * Tests the equality and hashCode methods of the Roles class.
   */
  @Test
  void testRolesEquality() {
    Roles role1 = new Roles(1, "Admin");
    Roles role2 = new Roles(1, "Admin");
    assertThat(role1).isEqualTo(role2);
    assertThat(role1.hashCode()).isEqualTo(role2.hashCode());

    role2.setTitle("User");
    assertThat(role1).isNotEqualTo(role2);
  }

  /**
   * Tests the default constructor of the Roles class.
   */
  @Test
  void testRolesDefaultConstructor() {
    Roles role = new Roles();
    assertThat(role.getId()).isNull();
    assertThat(role.getTitle()).isNull();
  }
}
