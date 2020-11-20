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

import com.cenfotec.tecasa.domain.Antology;
import com.cenfotec.tecasa.domain.Article;
import com.cenfotec.tecasa.service.AntologyService;
import com.cenfotec.tecasa.service.ArticleService;



@Controller
public class AntologyController {

	@Autowired
	AntologyService anthologyService;
	
	@Autowired
	ArticleService articleService; 
	
	@RequestMapping("/")
	public String home(Model model) {
		return "index";
	}
	
	@RequestMapping(value = "/insertar",  method = RequestMethod.GET)
	public String insertarPage(Model model) {
		model.addAttribute(new Antology());
		return "insertar";
	}	
	
	@RequestMapping(value = "/insertar",  method = RequestMethod.POST)
	public String insertarAction(Antology antology, BindingResult result, Model model) {
		anthologyService.save(antology);
		return "index";
	}
	
	@RequestMapping("/listar")
	public String listar(Model model) {
		model.addAttribute("anthologies",anthologyService.getAll());
		return "listar";
	}
	
	
	
	@GetMapping("/editar/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Optional<Antology> antology = anthologyService.get(id);

		if (antology.isPresent()) {
			model.addAttribute("antology", antology);
			return "editar";
		}
			return "index";
	}
	
	@PostMapping("/editar/{id}")
	public String updateAntology(@PathVariable("id") long id, Antology antology, BindingResult result, Model model) {
		
			
			if(result.hasErrors()) {
				antology.setId(id);
				return "editar/{id}";
			}
			anthologyService.save(antology);
			model.addAttribute("anthologies", anthologyService.getAll());
			return "listar";
			
		}
		
	

		@RequestMapping(value = "/agregarArticulo/{id}")
		public String recoverForAddArticle(Model model, @PathVariable long id) {
			Optional<Antology> antology = anthologyService.get(id);
			Article newArticle = new Article();
			if (antology.isPresent()) {
				newArticle.setAnthology(antology.get());
				model.addAttribute("antology", antology.get());
				model.addAttribute("article", newArticle);
				return "agregarArticulo";
			}
			return "noEncontrada";
		}

		@RequestMapping(value = "/agregarArticulo/{id}", method = RequestMethod.POST)
		public String saveArticle(Article article, Model model, @PathVariable long id) {
			Optional<Antology> antology = anthologyService.get(id);
			if (antology.isPresent()) {
				article.setAnthology(antology.get());
				articleService.save(article);
				return "index";
			}
			return "errorArticle";
		}

		
		@RequestMapping(value="/detalle/{id}")
		public String saveEdition(Model model, @PathVariable long id) {
			Optional<Antology> possibleData = anthologyService.get(id);
			if (possibleData.isPresent()) {
				model.addAttribute("antologyData",possibleData.get());
				return "detalle";	
			}
			return "noEncontrada";
		}
		
		
	}
	
	

