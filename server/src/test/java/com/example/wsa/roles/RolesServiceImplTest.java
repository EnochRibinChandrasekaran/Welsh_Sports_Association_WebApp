package com.example.wsa.roles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * Test class for {@link RolesServiceImpl}.
 * Contains unit tests for the RolesServiceImpl methods.
 */
public class RolesServiceImplTest {

  @Mock
  private RolesRepository rolesRepository;

  @InjectMocks
  private RolesServiceImpl rolesService;

  /**
   * Initializes mocks before each test case.
   */
  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  /**
   * Tests the successful retrieval of all roles.
   */
  @Test
  void testGetAllRoles_Pass() {
    Roles role1 = new Roles(1, "Admin");
    Roles role2 = new Roles(2, "User");
    when(rolesRepository.findAll()).thenReturn(Arrays.asList(role1, role2));
    List<Roles> result = rolesService.getAllRoles();
    assertThat(result).hasSize(2);
    assertThat(result.get(0).getTitle()).isEqualTo("Admin");
    assertThat(result.get(1).getTitle()).isEqualTo("User");
  }

  /**
   * Tests the scenario where retrieval of all roles fails due to a database error.
   */
  @Test
  void testGetAllRoles_Fail() {
    doThrow(new RuntimeException("Database connection error")).when(rolesRepository).findAll();
    Exception exception = assertThrows(RuntimeException.class, () -> {
      rolesService.getAllRoles();
    });
    assertThat(exception.getMessage()).isEqualTo("Database connection error");
  }
}
