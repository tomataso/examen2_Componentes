package com.cenfotec.tecasa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cenfotec.tecasa.domain.Workshop;
import com.cenfotec.tecasa.repo.WorkshopRepository;

@Service
public class WorkshopServiceImpl implements WorkshopService {

	@Autowired
	WorkshopRepository repo;
	
	@Override
	public void save(Workshop workshop) {
		repo.save(workshop);
	}

	@Override
	public Optional<Workshop> get(Long id) {
		return repo.findById(id);
	}

	@Override
	public List<Workshop> find(String name) {
		return repo.findByNameContaining(name);
	}

	@Override
	public List<Workshop> getAll() {
		return repo.findAll();
	}
	
	
	/*
	
	@Override
	public List<Workshop> findCategoria(String categoria) {
		
		// Almacen de resultados.
		List<Workshop> listaCategoria= new ArrayList<Workshop>();
		
		// Cuenta cuantos objetos totales tiene el repo.
		for (int index = 0; index < repo.count(); index++) {
			
	        if (repo.getOne((long) index - 1).getCategoria() == categoria)
	        	
	        	listaCategoria.add(repo.getOne((long) index - 1));     	
	           
	    }
		 return listaCategoria;
	
	
		
	@Override
	public List<Workshop> findCategoria(String categoria) {
		
		return repo.findByNameContaining(categoria);
	
	
	}
*/
	
}

