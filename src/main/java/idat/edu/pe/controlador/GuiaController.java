package idat.edu.pe.controlador;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import idat.edu.pe.modelo.Guia;
import idat.edu.pe.servicio.GuiaService;

@Controller
@RequestMapping("/guia")
public class GuiaController {

	@Autowired(required = false)
	private GuiaService service;

	// listado de Guia
	@GetMapping("/listado")
	public String listaGuia(Model modelo) {
		modelo.addAttribute("guias", service.findAll());
		return "Servicios/guia";
	}

	// agregar Guia
	@GetMapping("/agregar")
	public String crearGuias(Model modelo) {
		Guia guia = new Guia();
		modelo.addAttribute("guias", guia);
		return "Servicios/frmGuia";
	}
	// validacion por el edit, y el delete
	@PostMapping("/save")
	public String guardar(@Valid @ModelAttribute Guia guia, BindingResult result, Model model, RedirectAttributes attribute) {
		
		if(result.hasErrors()) {
			model.addAttribute("guias", guia);
			System.out.println("Error en el formulario de Guia");
			attribute.addFlashAttribute("error","Error en el formulario!");
			return "Servicios/frmGuia";
		}
		service.insert(guia);
		attribute.addFlashAttribute("success","Guia guardado con exito!");
		return "redirect:/guia/listado";
	}
}
