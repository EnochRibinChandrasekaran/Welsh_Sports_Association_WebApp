package com.example.wsa.qualification;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
 * Test class for {@link QualificationController}.
 * Contains unit tests for the QualificationController methods.
 */
public class QualificationControllerTest {

  @Mock
  private QualificationService qualificationService;

  @InjectMocks
  private QualificationController qualificationController;

  /**
   * Initializes mocks before each test case.
   */
  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  /**
   * Tests the successful retrieval of qualifications.
   *
   * @throws QualificationException if an error occurs during the test
   */
  @Test
  void testGetQualifications_Pass() throws QualificationException {
    Qualification qualification1 =
            new Qualification(1, "Bachelor of Science",
                    QualificationType.EDUCATIONAL);
    Qualification qualification2 =
            new Qualification(2, "Certified Java Developer",
                    QualificationType.PROFESSIONAL_CERTIFICATION);
    List<Qualification> mockQualifications = Arrays.asList(qualification1, qualification2);
    when(qualificationService.getAllQualifications()).thenReturn(mockQualifications);
    List<Qualification> result = qualificationController.getQualifications();

    assertEquals(2, result.size());
    assertEquals("Bachelor of Science", result.get(0).getName());
    verify(qualificationService, times(1)).getAllQualifications();
  }

  /**
   * Tests the scenario where retrieving qualifications fails.
   */
  @Test
  void testGetQualifications_Fail() {
    when(qualificationService.getAllQualifications())
            .thenThrow(new RuntimeException("Database error"));
    Exception exception = assertThrows(QualificationException.class, () -> {
      qualificationController.getQualifications();
    });

    assertEquals("Error fetching Qualifications", exception.getMessage());
    verify(qualificationService, times(1)).getAllQualifications();
  }
}
