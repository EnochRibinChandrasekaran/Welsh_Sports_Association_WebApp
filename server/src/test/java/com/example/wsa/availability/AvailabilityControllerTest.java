package com.example.wsa.availability;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/**
 * Unit tests for the {@link AvailabilityController} class.
 */
public class AvailabilityControllerTest {

  private AvailabilityService availabilityService;
  private AvailabilityController availabilityController;

  /**
   * Sets up the test environment before each test execution.
   */
  @BeforeEach
  void setUp() {
    availabilityService = Mockito.mock(AvailabilityService.class);
    availabilityController = new AvailabilityController(availabilityService);
  }

  /**
   * Tests the successful retrieval of all availabilities via the controller.
   *
   * @throws AvailabilityException if an error occurs during the test
   */
  @Test
  void testGetAllAvailabilities_Pass() throws AvailabilityException {
    List<Availability> mockAvailabilityList = Arrays.asList(
            new Availability(1, "Monday"),
            new Availability(2, "Tuesday")
    );
    when(availabilityService.getAllAvailabilities()).thenReturn(mockAvailabilityList);

    List<Availability> result = availabilityController.getAllAvailabilities();

    assertEquals(mockAvailabilityList, result);
    verify(availabilityService, times(1)).getAllAvailabilities();
  }

  /**
   * Tests the controller's handling of an exception thrown by the service layer.
   */
  @Test
  void testGetAllAvailabilities_Fail() {
    when(availabilityService.getAllAvailabilities())
            .thenThrow(new RuntimeException("Database error"));

    AvailabilityException exception = assertThrows(AvailabilityException.class, () -> {
      availabilityController.getAllAvailabilities();
    });

    assertEquals("Error fetching availability data", exception.getMessage());
    verify(availabilityService, times(1)).getAllAvailabilities();
  }
}
