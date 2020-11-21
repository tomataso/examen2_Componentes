package com.cenfotec.tecasa.service;

import java.util.List;
import java.util.Optional;

import com.cenfotec.tecasa.domain.Categoria;


public interface CategoriaService {

	public void save(Categoria categoria);
	public Optional<Categoria> get(Long id);
	public List<Categoria> getAll();
	public List<Categoria> find(String name);
	
	

	
}
