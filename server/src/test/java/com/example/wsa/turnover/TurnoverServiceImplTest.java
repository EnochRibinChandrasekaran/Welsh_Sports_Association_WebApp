package com.example.wsa.turnover;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * Test class for {@link TurnoverServiceImpl}.
 * Contains unit tests for the TurnoverServiceImpl methods.
 */
public class TurnoverServiceImplTest {

  @Mock
  private TurnoverRepository turnoverRepository;

  @InjectMocks
  private TurnoverServiceImpl turnoverService;

  /**
   * Initializes mocks before each test case.
   */
  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  /**
   * Tests the successful retrieval of all turnovers.
   */
  @Test
  void testGetAllTurnovers_Pass() {
    Turnover turnover1 = new Turnover(1, "Type1");
    Turnover turnover2 = new Turnover(2, "Type2");
    when(turnoverRepository.findAll()).thenReturn(Arrays.asList(turnover1, turnover2));

    // Act: Calling the service method
    List<Turnover> result = turnoverService.getAllTurnovers();

    // Assert: Verifying the results
    assertEquals(2, result.size());
    assertEquals("Type1", result.get(0).getTypes());
    assertEquals("Type2", result.get(1).getTypes());

    // Verify that the repository was called exactly once
    verify(turnoverRepository, times(1)).findAll();
  }

  /**
   * Tests the scenario where the repository returns an empty list.
   */
  @Test
  void testGetAllTurnovers_EmptyList() {
    // Arrange: Mocking the repository to return an empty list
    when(turnoverRepository.findAll()).thenReturn(Collections.emptyList());

    // Act: Calling the service method
    List<Turnover> result = turnoverService.getAllTurnovers();

    // Assert: Verifying the results
    assertEquals(0, result.size());

    // Verify that the repository was called exactly once
    verify(turnoverRepository, times(1)).findAll();
  }

  /**
   * Tests the scenario where retrieving turnovers fails due to a database error.
   */
  @Test
  void testGetAllTurnovers_Fail() {
    // Arrange: Mocking the repository to throw an exception
    when(turnoverRepository.findAll()).thenThrow(new RuntimeException("Database Error"));

    // Act & Assert: Expecting a RuntimeException to be thrown
    RuntimeException exception = assertThrows(RuntimeException.class, () -> {
      turnoverService.getAllTurnovers();
    });

    assertEquals("Database Error", exception.getMessage());

    // Verify that the repository was called exactly once
    verify(turnoverRepository, times(1)).findAll();
  }
}
