package com.gym.edition1.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GenericResponse<T> {

  // Indicates if the request was successful
  private boolean success;

  // HTTP status code (e.g., 200, 201, 400, 500)
  private int statusCode;

  // Human-readable message (e.g., "Member created successfully", "Validation
  // failed")
  private String message;

  // Actual payload/data (can be null for void operations like DELETE)

  private T data;

  // Optional: Timestamp of response (useful for debugging)
  private String timestamp;


  // ✅ Static factory methods for convenience
  public static <T> GenericResponse<T> success(T data, String message) {
    return GenericResponse.<T>builder()
        .success(true)
        .statusCode(200)
        .message(message)
        .data(data)
        .timestamp(java.time.LocalDateTime.now().toString())
        .build();
  }

  public static <T> GenericResponse<T> created(T data, String message) {
    return GenericResponse.<T>builder()
        .success(true)
        .statusCode(201)
        .message(message)
        .data(data)
        .timestamp(java.time.LocalDateTime.now().toString())
        .build();
  }

  public static <T> GenericResponse<T> updated(T data, String message) {
    return GenericResponse.<T>builder()
        .success(true)
        .statusCode(200)
        .message(message)
        .data(data)
        .timestamp(java.time.LocalDateTime.now().toString())
        .build();
  }

  public static <T> GenericResponse<T> error(int statusCode, String message) {
    return GenericResponse.<T>builder()
        .success(false)
        .statusCode(statusCode)
        .message(message)
        .data(null)
        .timestamp(java.time.LocalDateTime.now().toString())
        .build();
  }
}
