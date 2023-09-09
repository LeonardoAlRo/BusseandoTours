package idat.edu.pe.controlador;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.security.auth.message.callback.SecretKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lowagie.text.DocumentException;

import idat.edu.pe.modelo.Venta;
import idat.edu.pe.modelo.Paquete;
import idat.edu.pe.modelo.Pasajero;
import idat.edu.pe.modelo.Usuario;
import idat.edu.pe.modelo.Reserva;
import idat.edu.pe.repositorio.UsuarioRepositorio;
import idat.edu.pe.repositorio.VentasRepositorio;
import idat.edu.pe.servicio.PaqueteService;
import idat.edu.pe.servicio.PasajeroService;
import idat.edu.pe.servicio.ReservasService;
import idat.edu.pe.servicio.UsuarioServicio;
import idat.edu.pe.servicio.VentasService;
//import idat.edu.pe.util.reportes.BoletaExporterPDF;
//import idat.edu.pe.util.reportes.FacturaExporterPDF;
//import idat.edu.pe.util.reportes.VentaExporterExcel;
//import idat.edu.pe.util.reportes.VentaExporterPDF;


@Controller
@RequestMapping("/reservas")
public class ReservasController {
	
	@Autowired(required = false)
	private ReservasService service;
	
	@Autowired(required = false)
	private PaqueteService servicepaq;
	
	@Autowired(required = false)
	private PasajeroService servicepas;
	
	@Autowired(required = false)
	private UsuarioServicio servicesus;

	@GetMapping("/listar")
	public String verPaginaDeReservas(Model modelo, @Param("palabraClave")String palabraClave) {
		modelo.addAttribute("reservas", service.listAll(palabraClave));
		
		return "Reserva/reservas";
	}

		@GetMapping("/{idPaquete}/agregarR")
		public String crearReserva(Model modelo,@PathVariable("idPaquete") int idPaquete) {
			Paquete paquete = servicepaq.findById(idPaquete);
			Reserva reserva = new Reserva();
			
			modelo.addAttribute("paquete", paquete);
			modelo.addAttribute("reservas", reserva);
			return "Reserva/frmReserva";
		}
	
	@GetMapping("/listarreserva/{id}")
	public String listaReservas(Model modelo,  @PathVariable("id") Long id) {
		Optional<Usuario> usuario = servicesus.findById(id);
	    if (usuario.isPresent()) {
	        List<Reserva> reservas = usuario.get().getReservas();
	        
	        modelo.addAttribute("reservas", reservas);
	        return "Reserva/MisReservas";
	    } else {
	        return "";
	    }
	}

	@PostMapping("/a{idPaquete}/saveReserva")
	public String guardar( @Valid @ModelAttribute Reserva reserva, BindingResult result, Model model, RedirectAttributes attribute) {	
		if(result.hasErrors()) {
			model.addAttribute("reserva", reserva);
			return "Reserva/frmReserva";
		}
		// Se agrega la venta al modelo y se muestra el formulario correspondiente
	    
	    
		service.insert(reserva);
		attribute.addFlashAttribute("success","Reserva registrada con exito!");
		 return "redirect:/";
	}
	
	
	
	
	@GetMapping("/delete/{idReserva}")
	public String eliminarPaquetes(@PathVariable("idReserva") Integer idReserva, RedirectAttributes attribute, HttpServletRequest request) {
	    Reserva reserva = service.findById(idReserva);

	    if(reserva.getEstado().equals("PAGADO")) {
	    	attribute.addFlashAttribute("success", "Ya pagó esta reserva, no puede ser eliminada/editada!!");
	    	String referer = request.getHeader("Referer");
		    return "redirect:" + referer;
	    } else {
	        service.delete(idReserva);
	        attribute.addFlashAttribute("warning", "Reserva eliminada con éxito!");
	    }
	    
	    String referer = request.getHeader("Referer");
	    return "redirect:" + referer;
	}
}
