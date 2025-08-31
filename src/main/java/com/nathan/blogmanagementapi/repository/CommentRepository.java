package com.nathan.blogmanagementapi.repository;

import com.nathan.blogmanagementapi.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for Comment entity.
 * Provides database operations for Comment objects.
 * Extends JpaRepository to inherit basic CRUD operations.
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    /**
     * Custom method to find all comments for a specific article.
     *
     * @param articleId The ID of the article for which to find comments
     * @return List of comments belonging to the specified article
     */
    List<Comment> findByArticleId(Long articleId);
}
