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

import idat.edu.pe.modelo.ServicioHoteleria;
import idat.edu.pe.servicio.ServicioHoteleriaService;

@Controller
@RequestMapping("/serviciohoteleria")
public class ServicioHoteleriaController {

	@Autowired(required = false)
	private ServicioHoteleriaService service;

	// listado de Servicio Hotelero
	@GetMapping("/listado")
	public String listaServicioHoteleria(Model modelo) {
		modelo.addAttribute("serviciohotelerias", service.findAll());
		return "Servicios/serviciohoteleria";
	}

	// agregar Servicio Hotelero
	@GetMapping("/agregar")
	public String crearServicioHoteleria(Model modelo) {
		ServicioHoteleria serviciohoteleria = new ServicioHoteleria();
		modelo.addAttribute("serviciohotelerias", serviciohoteleria);
		return "Servicios/frmServicioHoteleria";
	}
	//validacion por el edit, y el delete
	
	@PostMapping("/save")
	public String guardar(@Valid @ModelAttribute ServicioHoteleria serviciohoteleria, BindingResult result, Model model, RedirectAttributes attribute) {
		
		if(result.hasErrors()) {
			model.addAttribute("serviciohotelerias", serviciohoteleria);
			System.out.println("Error en el formulario Servicio Hotelero");
			attribute.addFlashAttribute("error","Error en el formulario!");
			return "Servicios/frmServicioHoteleria";
		}
		service.insert(serviciohoteleria);
		attribute.addFlashAttribute("success","Servicio Hotelero fue guardado con exito!");
		return "redirect:/serviciohoteleria/listado";
	}

}
