package com.nathan.blogmanagementapi.service;

import com.nathan.blogmanagementapi.dto.CommentDto;
import com.nathan.blogmanagementapi.exception.ResourceNotFoundException;
import com.nathan.blogmanagementapi.model.Article;
import com.nathan.blogmanagementapi.model.Comment;
import com.nathan.blogmanagementapi.repository.ArticleRepository;
import com.nathan.blogmanagementapi.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for Comment operations.
 * Contains business logic for creating and retrieving comments.
 */
@Service
@RequiredArgsConstructor
public class CommentService {

    /**
     * Repository for Comment entity operations.
     * Injected through constructor (RequiredArgsConstructor).
     */
    private final CommentRepository commentRepository;

    /**
     * Repository for Article entity operations.
     * Injected through constructor (RequiredArgsConstructor).
     */
    private final ArticleRepository articleRepository;

    /**
     * Retrieves all comments for a specific article.
     *
     * @param articleId The ID of the article for which to retrieve comments
     * @return List of comments for the specified article
     * @throws ResourceNotFoundException if article is not found
     */
    public List<CommentDto.Response> getCommentsByArticleId(Long articleId) {
        // Verify article exists
        if (!articleRepository.existsById(articleId)) {
            throw new ResourceNotFoundException("Article not found with id: " + articleId);
        }

        return commentRepository.findByArticleId(articleId).stream()
                .map(this::mapToCommentResponse)
                .collect(Collectors.toList());
    }

    /**
     * Creates a new comment for an article.
     *
     * @param articleId The ID of the article to which the comment belongs
     * @param commentRequest The comment data to create
     * @return The created comment as a response DTO
     * @throws ResourceNotFoundException if article is not found
     */
    public CommentDto.Response createComment(Long articleId, CommentDto.Request commentRequest) {
        // Find the article to which this comment belongs
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new ResourceNotFoundException("Article not found with id: " + articleId));

        // Create and save the new comment
        Comment comment = new Comment();
        comment.setContent(commentRequest.getContent());
        comment.setArticle(article);

        Comment savedComment = commentRepository.save(comment);
        return mapToCommentResponse(savedComment);
    }

    /**
     * Maps a Comment entity to a response DTO.
     *
     * @param comment The comment entity to map
     * @return Mapped comment response DTO
     */
    private CommentDto.Response mapToCommentResponse(Comment comment) {
        CommentDto.Response response = new CommentDto.Response();
        response.setId(comment.getId());
        response.setContent(comment.getContent());
        response.setArticleId(comment.getArticle().getId());
        response.setCreatedAt(comment.getCreatedAt());
        return response;
    }
}
