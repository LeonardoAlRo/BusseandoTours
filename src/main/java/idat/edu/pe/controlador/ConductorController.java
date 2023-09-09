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

import idat.edu.pe.modelo.Conductor;
import idat.edu.pe.servicio.ConductorService;

@Controller
@RequestMapping("/conductor")
public class ConductorController {
	
	@Autowired(required = false)
	private ConductorService service;
	
	//listado de Conductor
	@GetMapping("/listado")
	public String listaConductor(Model modelo) {
		modelo.addAttribute("conductores", service.findAll());
		return "Servicios/conductor";
	}
	
	//agregar conductor
	@GetMapping("/agregar")
	public String crearConductores(Model modelo) {
		Conductor conductor = new Conductor();
		modelo.addAttribute("conductores", conductor);
		return "Servicios/frmConductor";
	}
	
	//validacion por el edit, y el delete
	
	@PostMapping("/save")
	public String guardar(@Valid @ModelAttribute Conductor conductor, BindingResult result, Model model, RedirectAttributes attribute) {
		if(result.hasErrors()) {
			model.addAttribute("conductores", conductor);
			System.out.println("Error en el formulario Conductor");
			attribute.addFlashAttribute("error","Error en el formulario!");
			return "Servicios/frmConductor";
		}
		service.insert(conductor);
		attribute.addFlashAttribute("success","Conductor guardado con exito!");
		return "redirect:/conductor/listado";
	}
	
}
