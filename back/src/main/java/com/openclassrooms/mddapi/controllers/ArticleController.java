package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.mapper.ArticleMapper;
import com.openclassrooms.mddapi.models.Article;
import com.openclassrooms.mddapi.models.Theme;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.security.jwt.AuthTokenFilter;
import com.openclassrooms.mddapi.services.ArticleService;
import com.openclassrooms.mddapi.services.ThemeService;
import com.openclassrooms.mddapi.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);
    private final ArticleService articleService;
    private final UserService userService;
    private final ThemeService themeService;
    private final ArticleMapper articleMapper;

    public ArticleController(ArticleService articleService, ArticleMapper articleMapper, UserService userService, ThemeService themeService) {
        this.articleService = articleService;
        this.articleMapper = articleMapper;
        this.userService = userService;
        this.themeService = themeService;
    }

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        List<Article> articles = this.articleService.findAll();

        return ResponseEntity.ok().body(this.articleMapper.toDto(articles));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") String id) {
        try {
            Article article = this.articleService.findById(Long.valueOf(id));

            if (article == null) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok().body(this.articleMapper.toDto(article));
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Create a new article and link it to the current user
     * @param article
     * @return
     */
    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody Article article, Authentication authentication ) {
        try {
            String currentUserId = authentication.getName();

            logger.info("currentUserId: " + currentUserId);

            User currentUser = this.userService.findByEmail(currentUserId);
            Theme currentTheme = this.themeService.findById(article.getTheme().getId());

            Article newArticle = new Article();
            newArticle.setTitle(article.getTitle());
            newArticle.setContent(article.getContent());
            newArticle.setTheme(currentTheme);
            newArticle.setUser(currentUser);

            Article createdArticle = this.articleService.create(newArticle);

            return ResponseEntity.ok().body(this.articleMapper.toDto(createdArticle));
        } catch (Exception e) {
            logger.error("An error occurred while creating an article: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
