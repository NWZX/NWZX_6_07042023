package com.openclassrooms.mddapi.controllers;

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

    @GetMapping("/article/{id}")
    public ResponseEntity<?> findByArticleId(@PathVariable Long id) {
        Article article = this.articleService.findById(id);

        if (article == null) {
            return ResponseEntity.notFound().build();
        }

        List<Comment> comments = this.commentService.findAllByArticle(id);

        return ResponseEntity.ok().body(this.commentMapper.toDto(comments));
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody Comment comment, Authentication authentication) {
        Article article = this.articleService.findById(comment.getArticle().getId());
        User user = this.userService.findByEmail(authentication.getName());

        if (article == null || user == null) {
            return ResponseEntity.notFound().build();
        }

        Comment newComment = new Comment();
        newComment.setArticle(article);
        newComment.setContent(comment.getContent());
        newComment.setUser(user);

        Comment createdComment = this.commentService.create(newComment);

        return ResponseEntity.ok().body(this.commentMapper.toDto(createdComment));
    }

}
