package com.nathan.blogmanagementapi.service;

import com.nathan.blogmanagementapi.dto.ArticleDto;
import com.nathan.blogmanagementapi.dto.CommentDto;
import com.nathan.blogmanagementapi.exception.ResourceNotFoundException;
import com.nathan.blogmanagementapi.model.Article;
import com.nathan.blogmanagementapi.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for Article operations.
 * Contains business logic for creating, retrieving, updating, and deleting articles.
 */
@Service
@RequiredArgsConstructor
public class ArticleService {

    /**
     * Repository for Article entity operations.
     * Injected through constructor (RequiredArgsConstructor).
     */
    private final ArticleRepository articleRepository;

    /**
     * Retrieves all articles from the database.
     *
     * @return List of articles converted to DTO responses
     */
    public List<ArticleDto.Response> getAllArticles() {
        return articleRepository.findAll().stream()
                .map(this::mapToArticleResponse)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a specific article by its ID.
     * Includes detailed information with comments.
     *
     * @param id The ID of the article to retrieve
     * @return Detailed article response with comments
     * @throws ResourceNotFoundException if article is not found
     */
    public ArticleDto.DetailedResponse getArticleById(Long id) {
        Article article = findArticleById(id);
        return mapToDetailedArticleResponse(article);
    }

    /**
     * Creates a new article from the request data.
     *
     * @param articleRequest The article data to create
     * @return The created article as a response DTO
     */
    public ArticleDto.Response createArticle(ArticleDto.Request articleRequest) {
        Article article = new Article();
        article.setTitle(articleRequest.getTitle());
        article.setContent(articleRequest.getContent());

        Article savedArticle = articleRepository.save(article);
        return mapToArticleResponse(savedArticle);
    }

    /**
     * Updates an existing article with new data.
     *
     * @param id The ID of the article to update
     * @param articleRequest The new article data
     * @return The updated article as a response DTO
     * @throws ResourceNotFoundException if article is not found
     */
    public ArticleDto.Response updateArticle(Long id, ArticleDto.Request articleRequest) {
        Article article = findArticleById(id);

        article.setTitle(articleRequest.getTitle());
        article.setContent(articleRequest.getContent());

        Article updatedArticle = articleRepository.save(article);
        return mapToArticleResponse(updatedArticle);
    }

    /**
     * Deletes an article by its ID.
     *
     * @param id The ID of the article to delete
     * @throws ResourceNotFoundException if article is not found
     */
    public void deleteArticle(Long id) {
        Article article = findArticleById(id);
        articleRepository.delete(article);
    }

    /**
     * Helper method to find an article by ID.
     *
     * @param id The ID of the article to find
     * @return The found article
     * @throws ResourceNotFoundException if article is not found
     */
    private Article findArticleById(Long id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Article not found with id: " + id));
    }

    /**
     * Maps an Article entity to a basic response DTO.
     *
     * @param article The article entity to map
     * @return Mapped article response DTO
     */
    private ArticleDto.Response mapToArticleResponse(Article article) {
        ArticleDto.Response response = new ArticleDto.Response();
        response.setId(article.getId());
        response.setTitle(article.getTitle());
        response.setContent(article.getContent());
        response.setCreatedAt(article.getCreatedAt());
        response.setUpdatedAt(article.getUpdatedAt());
        response.setCommentCount(article.getComments().size());
        return response;
    }

    /**
     * Maps an Article entity to a detailed response DTO, including comments.
     *
     * @param article The article entity to map
     * @return Mapped detailed article response DTO with comments
     */
    private ArticleDto.DetailedResponse mapToDetailedArticleResponse(Article article) {
        ArticleDto.DetailedResponse response = new ArticleDto.DetailedResponse();
        response.setId(article.getId());
        response.setTitle(article.getTitle());
        response.setContent(article.getContent());
        response.setCreatedAt(article.getCreatedAt());
        response.setUpdatedAt(article.getUpdatedAt());

        // Map all comments to comment DTOs
        response.setComments(article.getComments().stream()
                .map(comment -> {
                    CommentDto.Response commentResponse = new CommentDto.Response();
                    commentResponse.setId(comment.getId());
                    commentResponse.setContent(comment.getContent());
                    commentResponse.setArticleId(article.getId());
                    commentResponse.setCreatedAt(comment.getCreatedAt());
                    return commentResponse;
                })
                .collect(Collectors.toList()));

        return response;
    }
}
