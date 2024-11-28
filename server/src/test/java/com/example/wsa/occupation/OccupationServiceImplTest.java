package com.example.wsa.occupation;

import static org.assertj.core.api.Assertions.assertThat;
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
 * Unit tests for the {@link OccupationServiceImpl} class.
 */
public class OccupationServiceImplTest {

  @Mock
  private OccupationRepository occupationRepository;

  @InjectMocks
  private OccupationServiceImpl occupationService;

  /**
   * Initializes mocks before each test method.
   */
  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  /**
   * Tests the {@code getAllOccupations} method for a successful case.
   */
  @Test
  void testGetAllOccupations_Success() {
    Occupation occupation1 = new Occupation(1, "Engineer", "Technical");
    Occupation occupation2 = new Occupation(2, "Doctor", "Medical");

    List<Occupation> mockOccupations = Arrays.asList(occupation1, occupation2);
    when(occupationRepository.findAll()).thenReturn(mockOccupations);

    List<Occupation> result = occupationService.getAllOccupations();

    assertThat(result).isNotNull();
    assertThat(result.size()).isEqualTo(2);
    assertThat(result.get(0).getName()).isEqualTo("Engineer");

    verify(occupationRepository, times(1)).findAll();
  }

  /**
   * Tests the {@code getAllOccupations} method when the repository returns an empty list.
   */
  @Test
  void testGetAllOccupations_EmptyList() {
    when(occupationRepository.findAll()).thenReturn(Collections.emptyList());

    List<Occupation> result = occupationService.getAllOccupations();

    assertThat(result).isEmpty();
    verify(occupationRepository, times(1)).findAll();
  }

  /**
   * Tests the {@code getAllOccupations} method when a failure occurs.
   */
  @Test
  void testGetAllOccupations_Failure() {
    when(occupationRepository.findAll()).thenThrow(new RuntimeException("Database error"));

    RuntimeException exception = assertThrows(RuntimeException.class, () -> {
      occupationService.getAllOccupations();
    });

    assertThat(exception.getMessage()).isEqualTo("Database error");
    verify(occupationRepository, times(1)).findAll();
  }
}
