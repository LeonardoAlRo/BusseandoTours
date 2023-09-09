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

import idat.edu.pe.modelo.Alimentacion;
import idat.edu.pe.modelo.Bus;
import idat.edu.pe.servicio.BusService;

@Controller
@RequestMapping("/bus")
public class BusController {

	@Autowired(required = false)
	private BusService service;

	// listado de bus
	@GetMapping("/listado")
	public String listaBus(Model modelo) {
		modelo.addAttribute("buses", service.findAll());
		return "Servicios/bus";
	}

	// agregar bus
	@GetMapping("/agregar")
	public String crearBus(Model modelo) {
		Bus bus = new Bus();
		modelo.addAttribute("buses", bus);
		return "Servicios/frmBus";
	}
	// validacion por el edit, y el delete

	@PostMapping("/save")
	public String guardar(@Valid @ModelAttribute Bus bus, BindingResult result, Model model,
			RedirectAttributes attribute) {

		if (result.hasErrors()) {
			model.addAttribute("buses", bus);
			System.out.println("Error en el formulario Buses");
			attribute.addFlashAttribute("error", "Error en el formulario!");
			return "Servicios/frmBus";
		}
		service.insert(bus);
		attribute.addFlashAttribute("success", "Bus guardado con exito!");
		return "redirect:/bus/listado";
	}
}
