package com.cenfotec.tecasa.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cenfotec.tecasa.domain.Categoria;




public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
	public List<Categoria> findByNameContaining(String word);
	
	
}