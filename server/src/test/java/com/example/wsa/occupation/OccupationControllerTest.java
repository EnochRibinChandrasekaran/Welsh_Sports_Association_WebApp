package com.example.wsa.occupation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * Unit tests for the {@link OccupationController} class.
 */
public class OccupationControllerTest {

  @Mock
  private OccupationService occupationService;

  @InjectMocks
  private OccupationController occupationController;

  /**
   * Initializes mocks before each test method.
   */
  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  /**
   * Tests the {@code getAllOccupations} method for a successful case.
   *
   * @throws OccupationException if an error occurs while fetching occupations
   */
  @Test
  void testGetAllOccupations_Success() throws OccupationException {
    Occupation occupation1 = new Occupation(1, "Software Developer", "Technology");
    Occupation occupation2 = new Occupation(2, "Doctor", "Healthcare");

    List<Occupation> mockOccupations = Arrays.asList(occupation1, occupation2);
    when(occupationService.getAllOccupations()).thenReturn(mockOccupations);

    List<Occupation> result = occupationController.getAllOccupations();
    assertThat(result).hasSize(2);
    assertThat(result).contains(occupation1, occupation2);

    verify(occupationService, times(1)).getAllOccupations();
  }

  /**
   * Tests the {@code getAllOccupations} method for a failure case.
   *
   * @throws OccupationException expected to be thrown
   */
  @Test
  void testGetAllOccupations_Fail() throws OccupationException {
    when(occupationService.getAllOccupations())
            .thenThrow(new OccupationException("Error Fetching Occupations"));

    OccupationException exception = assertThrows(OccupationException.class, () -> {
      occupationController.getAllOccupations();
    });

    assertThat(exception.getMessage()).isEqualTo("Error Fetching Occupations");
    verify(occupationService, times(1)).getAllOccupations();
  }
}
