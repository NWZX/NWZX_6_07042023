package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.models.Article;
import com.openclassrooms.mddapi.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public void delete(Long id) {
        this.articleRepository.deleteById(id);
    }

    public Article findById(Long id) {
        return this.articleRepository.findById(id).orElse(null);
    }

    public List<Article> findAll() {
        return this.articleRepository.findAll();
    }

    public Article create(Article article) {
        return this.articleRepository.save(article);
    }
}
