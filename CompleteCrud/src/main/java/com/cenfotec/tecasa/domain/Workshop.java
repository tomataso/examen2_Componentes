package com.cenfotec.tecasa.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Workshop {

	// Atributos
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;	
	
	private String name;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="workshop")
	private Set<Tarea> tareas;
	
	private String autor;
	
	private String objetivo;
	
	private String categoria;
	
	private String pClaveUNO;
	
	private String pClaveDOS;
	
	private String pClaveTRES;
	
	private String duracion = "0";
	
	// Getters y Setters
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Tarea> getTareas() {
		return tareas;
	}

	public void setTareas(Set<Tarea> tareas) {
		this.tareas = tareas;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getpClaveUNO() {
		return pClaveUNO;
	}

	public void setpClaveUNO(String pClaveUNO) {
		this.pClaveUNO = pClaveUNO;
	}

	public String getpClaveDOS() {
		return pClaveDOS;
	}

	public void setpClaveDOS(String pClaveDOS) {
		this.pClaveDOS = pClaveDOS;
	}

	public String getpClaveTRES() {
		return pClaveTRES;
	}

	public void setpClaveTRES(String pClaveTRES) {
		this.pClaveTRES = pClaveTRES;
	}

	public String getDuracion() {
		return duracion;
	}

	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}
	


	
	
	
	
	
	
	


	
	
}
