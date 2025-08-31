package com.nathan.blogmanagementapi.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Data Transfer Objects for Article operations.
 * Contains nested classes for different request and response scenarios.
 */
public class ArticleDto {

    /**
     * DTO for incoming article creation and update requests.
     * Contains validation constraints for article fields.
     */
    @Data
    public static class Request {
        /**
         * Title of the article. Must not be blank and should be
         * between 3 and 100 characters.
         */
        @NotBlank(message = "Title is required")
        @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
        private String title;

        /**
         * Main content of the article. Must not be blank.
         */
        @NotBlank(message = "Content is required")
        private String content;
    }

    /**
     * DTO for basic article response data.
     * Used for article listing and after creation/update operations.
     */
    @Data
    public static class Response {
        /**
         * Unique identifier of the article.
         */
        private Long id;

        /**
         * Title of the article.
         */
        private String title;

        /**
         * Main content of the article.
         */
        private String content;

        /**
         * Timestamp when the article was created.
         */
        private LocalDateTime createdAt;

        /**
         * Timestamp when the article was last updated.
         */
        private LocalDateTime updatedAt;

        /**
         * Number of comments associated with this article.
         */
        private int commentCount;
    }

    /**
     * DTO for detailed article response data including comments.
     * Used when retrieving a specific article with all its comments.
     */
    @Data
    public static class DetailedResponse {
        /**
         * Unique identifier of the article.
         */
        private Long id;

        /**
         * Title of the article.
         */
        private String title;

        /**
         * Main content of the article.
         */
        private String content;

        /**
         * Timestamp when the article was created.
         */
        private LocalDateTime createdAt;

        /**
         * Timestamp when the article was last updated.
         */
        private LocalDateTime updatedAt;

        /**
         * List of comments associated with this article.
         */
        private List<CommentDto.Response> comments;
    }
}
