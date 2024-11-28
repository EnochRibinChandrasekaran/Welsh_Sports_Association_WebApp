package com.example.wsa.utility;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for {@link NotNullResponse}.
 * Contains unit tests for the NotNullResponse utility class.
 */
public class NotNullResponseTest {

  private TestClass testObject;

  /**
   * Sets up the test object before each test case.
   */
  @BeforeEach
  public void setUp() {
    testObject = new TestClass();
  }

  /**
   * Tests that non-null fields are correctly retrieved.
   */
  @Test
  public void testNonNullFields_Pass() {
    NotNullResponse<TestClass> response = new NotNullResponse<>(testObject);
    Map<String, Object> nonNullFields = response.getNonNullFields();

    assertEquals(2, nonNullFields.size());
    assertEquals("NonNullValue", nonNullFields.get("nonNullField"));
    assertEquals(123, nonNullFields.get("anotherNonNullField"));
  }

  /**
   * Tests that null fields are not included in the non-null fields map.
   */
  @Test
  public void testNullFields_Fail() {
    NotNullResponse<TestClass> response = new NotNullResponse<>(testObject);
    Map<String, Object> nonNullFields = response.getNonNullFields();

    assertFalse(nonNullFields.containsKey("nullField"));
  }

  /**
   * Tests that empty strings are included in the non-null fields map.
   */
  @Test
  public void testEmptyNonNullFields_Pass() {
    testObject.nonNullField = "";
    NotNullResponse<TestClass> response = new NotNullResponse<>(testObject);

    Map<String, Object> nonNullFields = response.getNonNullFields();
    assertEquals("", nonNullFields.get("nonNullField"));
  }

  /**
   * Tests that IllegalAccessException is handled gracefully.
   */
  @Test
  public void testIllegalAccessExceptionHandled() {
    var restrictedObject = new Object() {
      @SuppressWarnings("unused")
      private String restrictedField = "Restricted";
    };
    NotNullResponse<Object> response = new NotNullResponse<>(restrictedObject);
    Map<String, Object> nonNullFields = response.getNonNullFields();

    assertEquals(2, nonNullFields.size());
  }

  /**
   * Sample test class used for testing NotNullResponse.
   */
  private static class TestClass {
    public String nonNullField = "NonNullValue";
    public String nullField = null;
    public Integer anotherNonNullField = 123;
  }
}
