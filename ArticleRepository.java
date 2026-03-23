package com.kennedy.blog.repository;

import com.kennedy.blog.model.Article;
import com.kennedy.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findByAuteur(User auteur);

    List<Article> findByCategories(String categories);

    List<Article> findByTitreContainingIgnoreCase(String titre);

    List<Article> findByTagsContaining(String tag);

    List<Article> findAllByOrderByDateDesc();
}
