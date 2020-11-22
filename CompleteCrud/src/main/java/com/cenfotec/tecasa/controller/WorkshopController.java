package com.cenfotec.tecasa.controller;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
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

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.validation.BindingResult;

import com.cenfotec.tecasa.domain.Workshop;
import com.cenfotec.tecasa.domain.Categoria;
import com.cenfotec.tecasa.domain.Tarea;
import com.cenfotec.tecasa.service.WorkshopService;
import com.cenfotec.tecasa.service.CategoriaService;
import com.cenfotec.tecasa.service.TareaService;



@Controller
public class WorkshopController {

	public static String logo = "logo-leaf.png";
	public static String paragraph1 = "poi-word-para1.txt";
	public static String paragraph2 = "poi-word-para2.txt";
	public static String paragraph3 = "poi-word-para3.txt";
	public static String output = "rest-with-spring.docx";
	
	XWPFDocument document = new XWPFDocument();

	@Autowired
	WorkshopService workshopService;
	
	@Autowired
	TareaService tareaService; 
	
	@Autowired
	CategoriaService categoriaservice;
	
	@RequestMapping("/")
	public String home(Model model) {
		return "index";
	}
	
	@RequestMapping(value = "/insertarWorkshop",  method = RequestMethod.GET)
	public String insertarPage(Model model) {
		model.addAttribute(new Workshop());
		model.addAttribute ("categorias", categoriaservice.getAll());
		return "insertarWorkshop";
	}	
	
	@RequestMapping(value = "/insertarWorkshop",  method = RequestMethod.POST)
	public String insertarAction(Workshop workshop, BindingResult result, Model model) {
		workshopService.save(workshop);
		return "index";
	}
	
	@RequestMapping("/listarWorkshop")
	public String listar(Model model) {
		model.addAttribute("workshops",workshopService.getAll());
		
		return "listarWorkshop";
	}
	
	
	
	@GetMapping("/editarWorkshop/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Optional<Workshop> workshop = workshopService.get(id);

		if (workshop.isPresent()) {
			model.addAttribute("workshop", workshop);
			// ojo aca
			model.addAttribute ("categorias", categoriaservice.getAll());
			return "editarWorkshop";
		}
			return "index";
	}
	
	@PostMapping("/editarWorkshop/{id}")
	public String updateAntology(@PathVariable("id") long id, Workshop workshop, BindingResult result, Model model) {
		
			
			if(result.hasErrors()) {
				workshop.setId(id);
				return "editarWorkshop/{id}";
			}
			workshopService.save(workshop);
			model.addAttribute("workshops", workshopService.getAll());
			
			return "listarWorkshop";
			
		}
		
	

		@RequestMapping(value = "/agregarTarea/{id}")
		public String recoverForAddArticle(Model model, @PathVariable long id) {
			Optional<Workshop> workshop = workshopService.get(id);
			Tarea newTarea = new Tarea();
			if (workshop.isPresent()) {
				newTarea.setWorkshop(workshop.get());
				model.addAttribute("workshop", workshop.get());
				model.addAttribute("tarea", newTarea);
				return "agregarTarea";
				
				
				
			}
			return "noEncontrada";
		}

		@RequestMapping(value = "/agregarTarea/{id}", method = RequestMethod.POST)
		public String saveArticle(Tarea tarea, Model model, @PathVariable long id) {
			Optional<Workshop> workshop = workshopService.get(id);
			if (workshop.isPresent()) {
				tarea.setWorkshop(workshop.get());
				tareaService.save(tarea);
				
				// Aca se suma el tiempo para agregar en el general.
				int tareaTempo = Integer.parseInt(tarea.getTiempo());
				int workshopTempo = Integer.parseInt(workshop.get().getDuracion());
				int nuevoTempo = workshopTempo + tareaTempo;
				
				workshop.get().setDuracion(Integer.toString(nuevoTempo));
				workshopService.save(workshop.get());
				
				return "index";
			}
			return "errorArticle";
		}

		
		@RequestMapping(value="/detalleWorkshop/{id}")
		public String saveEdition(Model model, @PathVariable long id) {
			Optional<Workshop> possibleData = workshopService.get(id);
			if (possibleData.isPresent()) {
				model.addAttribute("workshopData",possibleData.get());
				return "detalleWorkshop";	
			}
			return "noEncontrada";
		}
		
		
		@RequestMapping("/listarPorCategoria")
		public String listarPorCategoria(Model model) {
			model.addAttribute("workshops",workshopService.getAll());
			return "listarPorCategoria";
		}
		
		
		@RequestMapping("/listarPorAutor")
		public String listarPorAutor(Model model) {
			model.addAttribute("workshops",workshopService.getAll());
			return "listarPorAutor";
		}
		
		@RequestMapping("/listarPorKeywords")
		public String listarPorKeywords(Model model) {
			model.addAttribute("workshops",workshopService.getAll());
			return "listarPorKeywords";
		}
		

		public String convertTextFileToString(String fileName) {
		    try (Stream<String> stream 
		      = Files.lines(Paths.get(ClassLoader.getSystemResource(fileName).toURI()))) {
		        
		        return stream.collect(Collectors.joining(" "));
		    } catch (IOException | URISyntaxException e) {
		        return null;
		    }
		}
		
		public void generarWord() throws IOException {
			FileOutputStream out = new FileOutputStream(output);
			document.write(out);
			out.close();
			document.close();
		}	
		
		@RequestMapping(value="/word/{id}")
		public String word(Model model, @PathVariable long id) throws IOException {
			Optional<Workshop> workshop = workshopService.get(id);
			Optional<Workshop> possibleData = workshopService.get(id);
		

			
			if (workshop.isPresent()) {
				model.addAttribute("workshop", workshop);
	
				model.addAttribute("workshopData",possibleData.get());
				
				
				
				this.modelarWord(workshop, possibleData);
				this.generarWord();

				return "index";
			}
				return "noEncontrada";
		}
		
	
		public void modelarWord(Optional<Workshop> workshop, 
				Optional<Workshop> possibleData) {

			
			XWPFParagraph para1 = document.createParagraph();
			XWPFRun para1Run = para1.createRun();
			para1Run.setText(" Workshop" + possibleData.get().getName());
			
			
			
			
			/*for (int i = 0; i < possibleData.get().getTareas().size(); i++) {
				
				if(possibleData.get().getId() == ) {
					
				}
				*/
			
			}
		}
	
	
	
	

