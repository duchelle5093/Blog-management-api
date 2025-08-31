package com.nathan.blogmanagementapi.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Standardized error response model for API error messages.
 * Used to provide consistent error responses across the API.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    /**
     * HTTP status code of the error.
     */
    private int status;

    /**
     * Descriptive error message.
     */
    private String message;

    /**
     * Timestamp when the error occurred.
     */
    private LocalDateTime timestamp;
}