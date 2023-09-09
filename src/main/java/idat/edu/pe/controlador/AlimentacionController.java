package idat.edu.pe.controlador;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import idat.edu.pe.modelo.Alimentacion;
import idat.edu.pe.servicio.AlimentacionService;

@Controller
@RequestMapping("/alimentacion")
public class AlimentacionController {

	@Autowired(required = false)
	private AlimentacionService service;
	
	//listado de alimentacion
	@GetMapping("/listado")
	public String listaAlimentacion(Model modelo) {
		modelo.addAttribute("alimentaciones", service.findAll());
		return "Servicios/alimentacion";
	}
	
	//agregar alimentacion
	@GetMapping("/agregar")
	public String crearAlimentaciones(Model modelo) {
		Alimentacion alimentacion = new Alimentacion();
		modelo.addAttribute("alimentaciones", alimentacion);
		return "Servicios/frmAlimentacion";
	}
	//validacion por el edit, y el delete
	
	@PostMapping("/save")
	public String guardar(@Valid @ModelAttribute Alimentacion alimentacion, BindingResult result, Model model, RedirectAttributes attribute) {
		
		if(result.hasErrors()) {
			model.addAttribute("alimentaciones", alimentacion);
			System.out.println("Error en el formulario Alimentación");
			attribute.addFlashAttribute("error","Error en el formulario!");
			return "Servicios/frmAlimentacion";
		}
		service.insert(alimentacion);
		attribute.addFlashAttribute("success","Alimentación guardado con exito!");
		return "redirect:/alimentacion/listado";
	}
}
