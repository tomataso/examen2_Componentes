package com.cenfotec.tecasa.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cenfotec.tecasa.domain.Categoria;
import com.cenfotec.tecasa.service.CategoriaService;

@Controller
public class CategoriaController {

	
	@Autowired
	CategoriaService categoriaservice;

	
	
	@RequestMapping(value = "/insertarCategoria", method = RequestMethod.GET)
	public String insertarPage(Model model) {
		model.addAttribute(new Categoria());
		return "insertarCategoria";
	}

	@RequestMapping(value = "/insertarCategoria", method = RequestMethod.POST)
	public String insertarAction(Categoria categoria, BindingResult result, Model model) {
		categoriaservice.save(categoria);
		System.out.println("Aca va esta categoria al save" + categoria.getName());
		return "index";
	}

	@RequestMapping("/listarCategoria")
	public String listar(Model model) {
		model.addAttribute("categorias", categoriaservice.getAll());
		
		System.out.println("Aca va el arreglo del listado" + categoriaservice.getAll().get(0).getName());
		
		
		return "listarCategoria";
	}

	@GetMapping("/editarCategoria/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Optional<Categoria> categoria = categoriaservice.get(id);

		if (categoria.isPresent()) {
			model.addAttribute("categoria", categoria);

			return "editarCategoria";
		}
		return "index";
	}

	@PostMapping("/editarCategoria/{id}")
	public String updateAntology(@PathVariable("id") long id, Categoria categoria, BindingResult result, Model model) {

		if (result.hasErrors()) {

			categoria.setId(id);

			return "editarCategoria/{id}";
		}
		categoriaservice.save(categoria);
		model.addAttribute("categorias", categoriaservice.getAll());
		return "listarCategoria";

	}

}
