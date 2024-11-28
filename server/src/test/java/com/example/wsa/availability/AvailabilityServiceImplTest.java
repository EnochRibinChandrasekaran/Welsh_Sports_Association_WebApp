package com.example.wsa.availability;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
 * Unit tests for the {@link AvailabilityServiceImpl} class.
 */
public class AvailabilityServiceImplTest {

  @Mock
  private AvailabilityRepository availabilityRepository;

  @InjectMocks
  private AvailabilityServiceImpl availabilityService;

  /**
   * Initializes mocks before each test execution.
   */
  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  /**
   * Tests the successful retrieval of all availabilities.
   */
  @Test
  void testGetAllAvailabilities_Pass() {
    List<Availability> mockAvailabilities = Arrays.asList(
            new Availability(1, "Monday"),
            new Availability(2, "Tuesday"),
            new Availability(3, "Wednesday")
    );
    when(availabilityRepository.findAll()).thenReturn(mockAvailabilities);
    List<Availability> result = availabilityService.getAllAvailabilities();

    assertEquals(3, result.size());
    assertEquals("Monday", result.get(0).getAvailableDays());
    assertEquals("Tuesday", result.get(1).getAvailableDays());
    assertEquals("Wednesday", result.get(2).getAvailableDays());
  }

  /**
   * Tests retrieval when the repository returns an empty list.
   */
  @Test
  void testGetAllAvailabilities_EmptyList() {
    when(availabilityRepository.findAll()).thenReturn(Collections.emptyList());
    List<Availability> result = availabilityService.getAllAvailabilities();
    assertEquals(0, result.size());
  }

  /**
   * Tests handling of an exception thrown by the repository.
   */
  @Test
  void testGetAllAvailabilities_Fail() {
    when(availabilityRepository.findAll()).thenThrow(new RuntimeException("Database error"));
    RuntimeException exception = assertThrows(RuntimeException.class, () -> {
      availabilityService.getAllAvailabilities();
    });
    assertEquals("Database error", exception.getMessage());
  }
}
