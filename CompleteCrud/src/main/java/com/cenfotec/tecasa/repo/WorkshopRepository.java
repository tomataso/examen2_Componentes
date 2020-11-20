package com.cenfotec.tecasa.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cenfotec.tecasa.domain.Workshop;

public interface WorkshopRepository extends JpaRepository<Workshop, Long>{
	public List<Workshop> findByNameContaining(String word);
}
