package com.cenfotec.tecasa.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cenfotec.tecasa.domain.Article;

public interface ArticleRepository extends JpaRepository<Article,Long>{

}
