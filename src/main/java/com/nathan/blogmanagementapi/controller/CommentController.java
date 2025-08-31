package com.nathan.blogmanagementapi.controller;

import com.nathan.blogmanagementapi.dto.CommentDto;
import com.nathan.blogmanagementapi.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for comment operations.
 * Provides endpoints for creating and retrieving comments.
 */
@RestController
@RequestMapping("/api/articles/{articleId}/comments")
@RequiredArgsConstructor
@Tag(name = "Comment Management", description = "APIs for managing article comments")
public class CommentController {

    /**
     * Service for comment operations.
     * Injected through constructor (RequiredArgsConstructor).
     */
    private final CommentService commentService;

    /**
     * Retrieves all comments for a specific article.
     *
     * @param articleId The ID of the article
     * @return ResponseEntity containing list of comments
     */
    @GetMapping
    @Operation(summary = "Get all comments for an article", description = "Returns all comments for a specific article")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comments retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Article not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<CommentDto.Response>> getCommentsByArticleId(@PathVariable Long articleId) {
        return ResponseEntity.ok(commentService.getCommentsByArticleId(articleId));
    }

    /**
     * Creates a new comment.
     *
     * @param commentRequest The comment data
     * @return ResponseEntity containing the created comment
     */
    @PostMapping
    @Operation(summary = "Add a new comment", description = "Adds a new comment to an article")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Comment created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Article not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<CommentDto.Response> createComment(
            @PathVariable Long articleId,
            @Valid @RequestBody CommentDto.Request commentRequest) {
        return new ResponseEntity<>(commentService.createComment(articleId, commentRequest), HttpStatus.CREATED);
    }
}
