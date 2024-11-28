package com.example.wsa.roles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * Test class for {@link RolesController}.
 * Contains unit tests for the RolesController methods.
 */
public class RolesControllerTest {

  @Mock
  private RolesService rolesService;

  @InjectMocks
  private RolesController rolesController;

  /**
   * Initializes mocks before each test case.
   */
  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  /**
   * Tests the successful retrieval of all roles.
   *
   * @throws RolesException if an error occurs during retrieval
   */
  @Test
  void testGetAllRoles_Pass() throws RolesException {
    List<Roles> mockRolesList = Arrays.asList(
            new Roles(1, "Admin"),
            new Roles(2, "User")
    );
    when(rolesService.getAllRoles()).thenReturn(mockRolesList);
    List<Roles> rolesList = rolesController.getAllRoles();
    assertThat(rolesList).isNotNull();
    assertThat(rolesList.size()).isEqualTo(2);
    assertThat(rolesList.get(0).getTitle()).isEqualTo("Admin");
    assertThat(rolesList.get(1).getTitle()).isEqualTo("User");
  }

  /**
   * Tests the scenario where retrieval of all roles fails.
   */
  @Test
  void testGetAllRoles_Fail() {
    when(rolesService.getAllRoles()).thenThrow(new RuntimeException("Database error"));
    RolesException exception = assertThrows(RolesException.class, () -> {
      rolesController.getAllRoles();
    });
    assertThat(exception.getMessage()).isEqualTo("Error fetching roles");
    assertThat(exception.getCause().getMessage()).isEqualTo("Database error");
  }
}
