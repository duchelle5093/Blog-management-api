package com.nathan.blogmanagementapi.controller;

import com.nathan.blogmanagementapi.dto.ArticleDto;
import com.nathan.blogmanagementapi.service.ArticleService;
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
 * REST controller for article operations.
 * Provides endpoints for CRUD operations on articles.
 */
@RestController
@RequestMapping("/api/articles")
@RequiredArgsConstructor
@Tag(name = "Article Management", description = "APIs for managing blog articles")
public class ArticleController {

    /**
     * Service for article operations.
     * Injected through constructor (RequiredArgsConstructor).
     */
    private final ArticleService articleService;

    /**
     * Retrieves all articles.
     *
     * @return ResponseEntity containing list of articles
     */
    @GetMapping
    @Operation(summary = "Get all articles", description = "Returns a list of all blog articles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Articles retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<ArticleDto.Response>> getAllArticles() {
        return ResponseEntity.ok(articleService.getAllArticles());
    }

    /**
     * Retrieves a specific article by ID.
     *
     * @param id The ID of the article to retrieve
     * @return ResponseEntity containing the article details
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get article by ID", description = "Returns a single article with its comments")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Article retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Article not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<ArticleDto.DetailedResponse> getArticleById(@PathVariable Long id) {
        return ResponseEntity.ok(articleService.getArticleById(id));
    }

    /**
     * Creates a new article.
     *
     * @param articleRequest The article data
     * @return ResponseEntity containing the created article
     */
    @PostMapping
    @Operation(summary = "Create a new article", description = "Creates a new blog article")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Article created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<ArticleDto.Response> createArticle(
            @Valid @RequestBody ArticleDto.Request articleRequest) {
        return new ResponseEntity<>(articleService.createArticle(articleRequest), HttpStatus.CREATED);
    }

    /**
     * Updates an existing article.
     *
     * @param id The ID of the article to update
     * @param articleRequest The updated article data
     * @return ResponseEntity containing the updated article
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update an article", description = "Updates an existing blog article")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Article updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Article not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<ArticleDto.Response> updateArticle(
            @PathVariable Long id,
            @Valid @RequestBody ArticleDto.Request articleRequest) {
        return ResponseEntity.ok(articleService.updateArticle(id, articleRequest));
    }

    /**
     * Deletes an article.
     *
     * @param id The ID of the article to delete
     * @return ResponseEntity with no content
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an article", description = "Deletes a blog article by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Article deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Article not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        articleService.deleteArticle(id);
        return ResponseEntity.noContent().build();
    }
}
