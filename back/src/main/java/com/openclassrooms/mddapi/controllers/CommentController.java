package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.dto.CommentDto;
import com.openclassrooms.mddapi.mapper.CommentMapper;
import com.openclassrooms.mddapi.models.Article;
import com.openclassrooms.mddapi.models.Comment;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.services.ArticleService;
import com.openclassrooms.mddapi.services.CommentService;
import com.openclassrooms.mddapi.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/comments")
public class CommentController {
    private final ArticleService articleService;

    private final UserService userService;

    private final CommentService commentService;

    private final CommentMapper commentMapper;

    public CommentController(ArticleService articleService, CommentService commentService, CommentMapper commentMapper, UserService userService) {
        this.articleService = articleService;
        this.commentService = commentService;
        this.commentMapper = commentMapper;
        this.userService = userService;
    }

    /**
     * Retrieves all comments associated with a given article ID.
     *
     * @param id the ID of the article to retrieve comments for
     * @return a ResponseEntity containing a list of CommentDto objects and an HTTP status code
     */
    @GetMapping("/article/{id}")
    public ResponseEntity<List<CommentDto>> findByArticleId(@PathVariable Long id) {
        Article article = this.articleService.findById(id);

        if (article == null) {
            return ResponseEntity.notFound().build();
        }

        List<Comment> comments = this.commentService.findAllByArticle(id);

        return ResponseEntity.ok().body(this.commentMapper.toDto(comments));
    }

    /**
     * Creates a new comment for a given article.
     *
     * @param comment the comment to be created
     * @param authentication the authentication object for the current user
     * @return a ResponseEntity containing the created CommentDto object and an HTTP status code
     */
    @PostMapping("")
    public ResponseEntity<CommentDto> create(@RequestBody Comment comment, Authentication authentication) {
        // Retrieve the article and user associated with the comment
        Article article = this.articleService.findById(comment.getArticle().getId());
        User user = this.userService.findByEmail(authentication.getName());

        // Check if the article and user exist
        if (article == null || user == null) {
            return ResponseEntity.notFound().build();
        }

        // Create a new comment object and set its properties
        Comment newComment = new Comment();
        newComment.setArticle(article);
        newComment.setContent(comment.getContent());
        newComment.setUser(user);

        // Create the comment and return a ResponseEntity containing the created CommentDto object
        Comment createdComment = this.commentService.create(newComment);
        return ResponseEntity.ok().body(this.commentMapper.toDto(createdComment));
    }

}
