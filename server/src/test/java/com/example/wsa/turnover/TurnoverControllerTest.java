package com.example.wsa.turnover;

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
 * Test class for {@link TurnoverController}.
 * Contains unit tests for the TurnoverController methods.
 */
public class TurnoverControllerTest {

  @Mock
  private TurnoverService turnoverService;

  @InjectMocks
  private TurnoverController turnoverController;

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
    List<Turnover> mockTurnovers = Arrays.asList(
            new Turnover(1, "Type 1"),
            new Turnover(2, "Type 2")
    );
    when(turnoverService.getAllTurnovers()).thenReturn(mockTurnovers);
    List<Turnover> result = turnoverController.getAllTurnovers();

    assertThat(result).isEqualTo(mockTurnovers);
    assertThat(result).hasSize(2);
    assertThat(result.get(0).getTypes()).isEqualTo("Type 1");
    assertThat(result.get(1).getTypes()).isEqualTo("Type 2");
  }

  /**
   * Tests the scenario where retrieval of turnovers fails due to an exception.
   */
  @Test
  void testGetAllTurnovers_Fail() {
    doThrow(new RuntimeException("Error retrieving turnover data"))
            .when(turnoverService).getAllTurnovers();
    RuntimeException exception = assertThrows(RuntimeException.class, () -> {
      turnoverController.getAllTurnovers();
    });
    assertThat(exception.getMessage()).isEqualTo("Error retrieving turnover data");
  }
}
