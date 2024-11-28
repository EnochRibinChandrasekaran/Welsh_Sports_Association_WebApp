package com.example.wsa.qualification;

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
 * Test class for {@link QualificationServiceImpl}.
 * Contains unit tests for the QualificationServiceImpl methods.
 */
public class QualificationServiceImplTest {

  @Mock
  private QualificationRespository qualificationRepository;

  @InjectMocks
  private QualificationServiceImpl qualificationService;

  /**
   * Initializes mocks before each test case.
   */
  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  /**
   * Tests the successful retrieval of all qualifications.
   */
  @Test
  void testGetAllQualifications_Pass() {
    // Arrange: Create a mock list of qualifications
    Qualification qualification1 =
            new Qualification(1, "Computer Science", QualificationType.EDUCATIONAL);
    Qualification qualification2 =
            new Qualification(2, "AWS Certification", QualificationType.PROFESSIONAL_CERTIFICATION);
    List<Qualification> mockQualifications = Arrays.asList(qualification1, qualification2);
    when(qualificationRepository.findAll()).thenReturn(mockQualifications);

    // Act: Call the method under test
    List<Qualification> result = qualificationService.getAllQualifications();

    // Assert: Verify the results
    assertThat(result).hasSize(2);
    assertThat(result.get(0).getName()).isEqualTo("Computer Science");
    assertThat(result.get(1).getName()).isEqualTo("AWS Certification");
    verify(qualificationRepository, times(1)).findAll();
  }

  /**
   * Tests the scenario where retrieving all qualifications fails.
   */
  @Test
  void testGetAllQualifications_Fail() {
    when(qualificationRepository.findAll()).thenThrow(new RuntimeException("Database error"));
    RuntimeException exception = assertThrows(RuntimeException.class, () -> {
      qualificationService.getAllQualifications();
    });

    assertThat(exception.getMessage()).isEqualTo("Database error");
    verify(qualificationRepository, times(1)).findAll();
  }
}
