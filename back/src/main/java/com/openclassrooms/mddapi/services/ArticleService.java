package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.models.Article;
import com.openclassrooms.mddapi.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This class represents the service layer for the Article entity.
 * It provides methods to create, find, delete and retrieve all articles.
 */
@Service
public class ArticleService {

    /**
     * The ArticleRepository instance used to interact with the database.
     */
    private final ArticleRepository articleRepository;

    /**
     * Constructor for the ArticleService class.
     * @param articleRepository The ArticleRepository instance used to interact with the database.
     */
    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    /**
     * Deletes an article from the database.
     * @param id The id of the article to delete.
     */
    public void delete(Long id) {
        this.articleRepository.deleteById(id);
    }

    /**
     * Finds an article in the database by its id.
     * @param id The id of the article to find.
     * @return The Article object if found, null otherwise.
     */
    public Article findById(Long id) {
        return this.articleRepository.findById(id).orElse(null);
    }

    /**
     * Retrieves all articles from the database.
     * @return A List of all Article objects in the database.
     */
    public List<Article> findAll() {
        return this.articleRepository.findAll();
    }

    /**
     * Creates a new article in the database.
     * @param article The Article object to create.
     * @return The created Article object.
     */
    public Article create(Article article) {
        return this.articleRepository.save(article);
    }
}
