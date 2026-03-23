package com.kennedy.blog.service;

import com.kennedy.blog.model.Article;
import com.kennedy.blog.model.User;
import com.kennedy.blog.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    public List<Article> findAllOrderByDateDesc() {
        return articleRepository.findAllByOrderByDateDesc();
    }

    public Optional<Article> findById(Long id) {
        return articleRepository.findById(id);
    }

    public List<Article> findByAuteur(User auteur) {
        return articleRepository.findByAuteur(auteur);
    }

    public List<Article> findByCategories(String categories) {
        return articleRepository.findByCategories(categories);
    }

    public List<Article> searchByTitre(String titre) {
        return articleRepository.findByTitreContainingIgnoreCase(titre);
    }

    public List<Article> findByTag(String tag) {
        return articleRepository.findByTagsContaining(tag);
    }

    public Article save(Article article) {
        if (article.getDate() == null) {
            article.setDate(LocalDate.now());
        }
        return articleRepository.save(article);
    }

    public Article update(Long id, Article articleDetails) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Article not found with id: " + id));

        article.setTitre(articleDetails.getTitre());
        article.setContenu(articleDetails.getContenu());
        article.setCategories(articleDetails.getCategories());
        article.setTags(articleDetails.getTags());

        return articleRepository.save(article);
    }

    public void deleteById(Long id) {
        articleRepository.deleteById(id);
    }
}
