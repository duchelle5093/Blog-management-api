package com.nathan.blogmanagementapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when a requested resource is not found.
 * Results in a 404 NOT FOUND HTTP response when thrown.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Constructor with error message.
     *
     * @param message The error message
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
