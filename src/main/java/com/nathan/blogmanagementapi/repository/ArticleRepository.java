package com.nathan.blogmanagementapi.repository;

import com.nathan.blogmanagementapi.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Article entity.
 * Provides database operations for Article objects.
 * Extends JpaRepository to inherit basic CRUD operations.
 */
@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    // We can add custom query methods here if needed
}
