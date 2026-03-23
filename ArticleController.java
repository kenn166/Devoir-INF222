package com.kennedy.blog.controller;

import com.kennedy.blog.model.Article;
import com.kennedy.blog.model.User;
import com.kennedy.blog.service.ArticleService;
import com.kennedy.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<Article>> getAllArticles() {
        List<Article> articles = articleService.findAllOrderByDateDesc();
        return ResponseEntity.ok(articles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticleById(@PathVariable Long id) {
        return articleService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/auteur/{auteurId}")
    public ResponseEntity<List<Article>> getArticlesByAuteur(@PathVariable Long auteurId) {
        return userService.findById(auteurId)
                .map(user -> ResponseEntity.ok(articleService.findByAuteur(user)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/categories/{categories}")
    public ResponseEntity<List<Article>> getArticlesByCategories(@PathVariable String categories) {
        List<Article> articles = articleService.findByCategories(categories);
        return ResponseEntity.ok(articles);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Article>> searchArticles(@RequestParam String titre) {
        List<Article> articles = articleService.searchByTitre(titre);
        return ResponseEntity.ok(articles);
    }

    @GetMapping("/tag/{tag}")
    public ResponseEntity<List<Article>> getArticlesByTag(@PathVariable String tag) {
        List<Article> articles = articleService.findByTag(tag);
        return ResponseEntity.ok(articles);
    }

    @PostMapping
    public ResponseEntity<Article> createArticle(@RequestBody Article article) {
        Article savedArticle = articleService.save(article);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedArticle);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long id, @RequestBody Article article) {
        try {
            Article updatedArticle = articleService.update(id, article);
            return ResponseEntity.ok(updatedArticle);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        if (articleService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        articleService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
