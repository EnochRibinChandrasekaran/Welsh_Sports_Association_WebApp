package com.example.wsa.utility;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A utility class that generates a response containing
 * only the non-null fields of a given data object.
 * This is useful for serializing objects to JSON without null values.
 *
 * @param <T> the type of the data object
 */
public class NotNullResponse<T> {

  /**
   * Logger instance for logging events.
   */
  private static final Logger logger = LoggerFactory.getLogger(NotNullResponse.class);

  /**
   * The original data object.
   */
  private final T data;

  /**
   * A map containing the names and values of non-null fields from the data object.
   */
  private final Map<String, Object> notNullFields = new HashMap<>();

  /**
   * Constructs a new {@code NotNullResponse} with the specified data object.
   * It populates the {@code notNullFields} map with non-null fields from the data object.
   *
   * @param data the data object to process
   */
  public NotNullResponse(T data) {
    this.data = data;
    populateNotNullFields();
  }

  /**
   * Populates the {@code notNullFields} map by reflecting over the data object's fields
   * and adding those that are not null.
   */
  private void populateNotNullFields() {
    for (var field : data.getClass().getDeclaredFields()) {
      try {
        field.setAccessible(true);
        Object value = field.get(data);
        if (value != null) {
          notNullFields.put(field.getName(), value);
          logger.debug("Added non-null field '{}' with value: {}", field.getName(), value);
        }
      } catch (IllegalAccessException e) {
        logger.error("Error accessing field '{}': {}", field.getName(), e.getMessage());
      }
    }
  }

  /**
   * Returns a map of the non-null fields of the data object.
   * This method is annotated with {@code @JsonAnyGetter}
   * to serialize the map directly into JSON properties.
   *
   * @return a map containing the non-null fields of the data object
   */
  @JsonAnyGetter
  public Map<String, Object> getNonNullFields() {
    return notNullFields;
  }
}
