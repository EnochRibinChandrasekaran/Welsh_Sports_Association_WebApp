package com.example.wsa.accessibility;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.example.wsa.accessibilty.Accessibility;
import com.example.wsa.accessibilty.AccessibilityRepository;
import com.example.wsa.accessibilty.AccessibilityServiceImpl;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * Unit tests for the {@link AccessibilityServiceImpl} class.
 */
public class AccessibilityServiceImplTest {

  @Mock
  private AccessibilityRepository accessibilityRepository;

  @InjectMocks
  private AccessibilityServiceImpl accessibilityService;

  /**
   * Initializes mocks before each test execution.
   */
  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  /**
   * Tests the successful retrieval of all accessibilities.
   */
  @Test
  void testGetAllAccessibilities_Pass() {
    Accessibility accessibility = new Accessibility(1, "Wheelchair Ramp");
    when(accessibilityRepository.findAll()).thenReturn(List.of(accessibility));

    List<Accessibility> result = accessibilityService.getAllAccessibilities();

    assertEquals(1, result.size());
    assertEquals("Wheelchair Ramp", result.get(0).getName());
  }

  /**
   * Tests retrieval when the repository returns an empty list.
   */
  @Test
  void testGetAllAccessibilities_Fail() {
    when(accessibilityRepository.findAll()).thenReturn(Collections.emptyList());

    List<Accessibility> result = accessibilityService.getAllAccessibilities();

    assertEquals(0, result.size());
  }

  /**
   * Tests handling of an exception thrown by the repository.
   */
  @Test
  void testGetAllAccessibilities_Exception() {
    when(accessibilityRepository.findAll()).thenThrow(new RuntimeException("Database error"));

    assertThrows(RuntimeException.class, () -> accessibilityService.getAllAccessibilities());
  }
}
