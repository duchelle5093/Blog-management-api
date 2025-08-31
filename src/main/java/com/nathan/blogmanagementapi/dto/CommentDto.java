package com.nathan.blogmanagementapi.dto;

import jakarta.validation.constraints.NotBlank;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Data Transfer Objects for Comment operations.
 * Contains nested classes for different request and response scenarios.
 */
public class CommentDto {

    /**
     * DTO for incoming comment creation requests.
     * Contains validation constraints for comment fields.
     */
    @Data
    public static class Request {
        /**
         * Content of the comment. Must not be blank.
         */
        @NotBlank(message = "Comment content is required")
        private String content;
    }

    /**
     * DTO for comment response data.
     * Used for comment listing and after creation operations.
     */
    @Data
    public static class Response {
        /**
         * Unique identifier of the comment.
         */
        private Long id;

        /**
         * Content of the comment.
         */
        private String content;

        /**
         * ID of the article to which this comment belongs.
         */
        private Long articleId;

        /**
         * Timestamp when the comment was created.
         */
        private LocalDateTime createdAt;
    }
}
