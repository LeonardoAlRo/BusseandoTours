package idat.edu.pe.controlador;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import idat.edu.pe.modelo.Reserva;
import idat.edu.pe.modelo.Usuario;
import idat.edu.pe.modelo.Pasajero;
import idat.edu.pe.servicio.ReservasService;
import idat.edu.pe.servicio.PasajeroService;


@Controller
@RequestMapping("/pasajero")
public class PasajeroController {
	
	@Autowired(required = false)
	private PasajeroService service;
	
	@Autowired(required = false)
	private ReservasService serviceres;
	

	@GetMapping("/listar")
	public String listaPasajeros(Model modelo) {
		modelo.addAttribute("pasajeros", service.findAll());
		return "Pasajero/pasajero";
	}
	
	@GetMapping("/{idReserva}/listarP")
	public String listaPasajeros(Model modelo, @PathVariable("idReserva") int idReserva) {
	    Reserva reserva = serviceres.findById(idReserva);
	    
	    if (reserva != null) {
	        List<Pasajero> pasajeros = reserva.getPasajero();
	        
	        modelo.addAttribute("pasajeros", pasajeros);
	        return "Pasajero/ListaPasajeros";
	    } else {
	        return ""; 
	    }
	}
	
	@GetMapping("/{idReserva}/agregar")
	public String crearPasajeros(Model modelo, @PathVariable("idReserva") int idReserva, RedirectAttributes attribute, HttpServletRequest request) {
	    Reserva reserva = serviceres.findById(idReserva);
	    Pasajero pasajero = new Pasajero();

	    if(reserva.getEstado().equals("PAGADO")) {
	    	attribute.addFlashAttribute("success", "Ya pago esta reserva");
	    	String referer = request.getHeader("Referer");
		    return "redirect:" + referer;
	    }
	    
	    if (reserva.getNroRegistrados() >= reserva.getNroPasajeros()) {
	    	attribute.addFlashAttribute("success", "Todos los pasajeros ya registrados");
	    	return "redirect:/pago/" + idReserva + "/metodoPago";
	    } else{
	        // Si aún hay espacio para registrar pasajeros en la reserva
	       
	    } 
	    	
		    modelo.addAttribute("reserva", reserva);
	        modelo.addAttribute("pasajeros", pasajero);
	        return "Pasajero/AddPasajero";
	}
	
	@PostMapping("/{idReserva}/save")
	public String guardar(@Valid @ModelAttribute Pasajero pasajero, BindingResult result, Model model, RedirectAttributes attribute, @PathVariable("idReserva") int idReserva) {
	    Reserva reserva = serviceres.findById(idReserva);

	    if (reserva.getNroRegistrados() >= reserva.getNroPasajeros()) {
	        attribute.addFlashAttribute("error", "Límite de pasajeros alcanzado");
	        return "redirect:/pago/" + idReserva + "/metodoPago?idPasajero=" + idReserva;
	    }

	    if (result.hasErrors()) {
	        model.addAttribute("pasajeros", pasajero);
	        attribute.addFlashAttribute("error", "Error en el formulario");
	        return "Pasajero/AddPasajero";
	    }
	    
	    // Incrementar el contador de pasajeros registrados en la reserva
	    reserva.setNroRegistrados(reserva.getNroRegistrados() + 1);
	    service.insert(pasajero);

	    int idPasajero = pasajero.getIdPasajero();
	    model.addAttribute("reserva", reserva);

	    if (reserva.getNroRegistrados() >= reserva.getNroPasajeros()) {
	    	   attribute.addFlashAttribute("success", "Todos los pasajeros registrados");
	        return "redirect:/pago/" + idReserva + "/metodoPago?idPasajero=" + idPasajero;
	    } else {
	        return "redirect:/pasajero/" + idReserva + "/agregar";
	    }
	}
}
