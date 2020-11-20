package com.cenfotec.tecasa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cenfotec.tecasa.domain.Tarea;
import com.cenfotec.tecasa.repo.TareaRepository;

@Service
public class TareaServiceImpl implements TareaService{

	@Autowired
	TareaRepository tareaRepo;
	
	
	public void save(Tarea tarea) {
		tareaRepo.save(tarea);
	}
	
}
