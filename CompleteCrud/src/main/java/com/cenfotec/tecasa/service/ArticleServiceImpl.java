package com.cenfotec.tecasa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cenfotec.tecasa.domain.Article;
import com.cenfotec.tecasa.repo.ArticleRepository;

@Service
public class ArticleServiceImpl implements ArticleService{

	@Autowired
	ArticleRepository articleRepo;
	
	
	public void save(Article article) {
		articleRepo.save(article);
	}
	
}
