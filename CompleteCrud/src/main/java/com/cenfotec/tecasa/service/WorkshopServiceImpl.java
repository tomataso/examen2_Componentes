package com.cenfotec.tecasa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

}
