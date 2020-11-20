package com.cenfotec.tecasa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;
import org.springframework.validation.BindingResult;

import com.cenfotec.tecasa.domain.Workshop;
import com.cenfotec.tecasa.domain.Tarea;
import com.cenfotec.tecasa.service.WorkshopService;
import com.cenfotec.tecasa.service.TareaService;



@Controller
public class WorkshopController {

	@Autowired
	WorkshopService workshopService;
	
	@Autowired
	TareaService tareaService; 
	
	@RequestMapping("/")
	public String home(Model model) {
		return "index";
	}
	
	@RequestMapping(value = "/insertar",  method = RequestMethod.GET)
	public String insertarPage(Model model) {
		model.addAttribute(new Workshop());
		return "insertar";
	}	
	
	@RequestMapping(value = "/insertar",  method = RequestMethod.POST)
	public String insertarAction(Workshop workshop, BindingResult result, Model model) {
		workshopService.save(workshop);
		return "index";
	}
	
	@RequestMapping("/listar")
	public String listar(Model model) {
		model.addAttribute("workshops",workshopService.getAll());
		return "listar";
	}
	
	
	
	@GetMapping("/editar/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Optional<Workshop> workshop = workshopService.get(id);

		if (workshop.isPresent()) {
			model.addAttribute("workshop", workshop);
			return "editar";
		}
			return "index";
	}
	
	@PostMapping("/editar/{id}")
	public String updateAntology(@PathVariable("id") long id, Workshop workshop, BindingResult result, Model model) {
		
			
			if(result.hasErrors()) {
				workshop.setId(id);
				return "editar/{id}";
			}
			workshopService.save(workshop);
			model.addAttribute("workshops", workshopService.getAll());
			return "listar";
			
		}
		
	

		@RequestMapping(value = "/agregarArticulo/{id}")
		public String recoverForAddArticle(Model model, @PathVariable long id) {
			Optional<Workshop> workshop = workshopService.get(id);
			Tarea newTarea = new Tarea();
			if (workshop.isPresent()) {
				newTarea.setWorkshop(workshop.get());
				model.addAttribute("workshop", workshop.get());
				model.addAttribute("tarea", newTarea);
				return "agregarArticulo";
			}
			return "noEncontrada";
		}

		@RequestMapping(value = "/agregarArticulo/{id}", method = RequestMethod.POST)
		public String saveArticle(Tarea tarea, Model model, @PathVariable long id) {
			Optional<Workshop> workshop = workshopService.get(id);
			if (workshop.isPresent()) {
				tarea.setWorkshop(workshop.get());
				tareaService.save(tarea);
				return "index";
			}
			return "errorArticle";
		}

		
		@RequestMapping(value="/detalle/{id}")
		public String saveEdition(Model model, @PathVariable long id) {
			Optional<Workshop> possibleData = workshopService.get(id);
			if (possibleData.isPresent()) {
				model.addAttribute("workshopData",possibleData.get());
				return "detalle";	
			}
			return "noEncontrada";
		}
		
		
	}
	
	

