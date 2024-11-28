package com.example.wsa.accessibility;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.wsa.accessibilty.Accessibility;
import com.example.wsa.accessibilty.AccessibilityController;
import com.example.wsa.accessibilty.AccessibilityException;
import com.example.wsa.accessibilty.AccessibilityService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * Unit tests for the {@link AccessibilityController} class.
 */
public class AccessibiltyControllerTest {

  private MockMvc mockMvc;

  @Mock
  private AccessibilityService accessibilityService;

  @InjectMocks
  private AccessibilityController accessibilityController;

  private ObjectMapper objectMapper = new ObjectMapper();

  /**
   * Sets up the test environment before each test execution.
   */
  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(accessibilityController).build();
  }

  /**
   * Tests the successful retrieval of all accessibilities via the controller.
   *
   * @throws Exception if an error occurs during the request
   */
  @Test
  void testGetAllAccessibility_Pass() throws Exception {
    List<Accessibility> mockAccessibilities = Arrays.asList(
            new Accessibility(1, "Wheelchair Ramp"),
            new Accessibility(2, "Elevator Access")
    );
    when(accessibilityService.getAllAccessibilities()).thenReturn(mockAccessibilities);

    MvcResult result = mockMvc.perform(get("/api/accessibility"))
            .andExpect(status().isOk())
            .andReturn();

    String jsonResponse = result.getResponse().getContentAsString();
    List<Accessibility> accessibilities = objectMapper
            .readValue(jsonResponse, new TypeReference<List<Accessibility>>() {
            });

    assertEquals(2, accessibilities.size());
    assertEquals("Wheelchair Ramp", accessibilities.get(0).getName());
    assertEquals("Elevator Access", accessibilities.get(1).getName());
  }

  /**
   * Tests the controller's handling of an exception thrown by the service layer.
   *
   * @throws Exception if an error occurs during the request
   */
  @Test
  void testGetAllAccessibility_Fail() throws Exception {
    when(accessibilityService.getAllAccessibilities())
            .thenThrow(new AccessibilityException("Error Fetching accessibility data"));

    assertThrows(AccessibilityException.class, () -> {
      accessibilityController.getAllAccessibility();
    });
  }

}
