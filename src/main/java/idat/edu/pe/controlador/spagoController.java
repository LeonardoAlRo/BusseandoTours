package idat.edu.pe.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import idat.edu.pe.modelo.Paquete;
import idat.edu.pe.modelo.Pasajero;
import idat.edu.pe.modelo.Reserva;
import idat.edu.pe.modelo.Venta;
import idat.edu.pe.servicio.PaqueteService;
import idat.edu.pe.servicio.PasajeroService;
import idat.edu.pe.servicio.ReservasService;


@Controller
@RequestMapping("/pago")
public class spagoController {
	
	@Autowired(required = false)
	private PaqueteService service;
	
	
	@Autowired(required = false)
	private PasajeroService pasajeroservice;
	
	
	

	@Autowired(required = false)
	private ReservasService serviceres;
	
	
	
	
	@GetMapping("/{idReserva}/metodoPago")
	public String metodoPago( Model model, @PathVariable("idReserva") int idReserva) {
	    // Lógica para obtener el paquete y el pasajero
		Reserva reserva = serviceres.findById(idReserva);
	    
	    model.addAttribute("reserva", reserva);
	    // Resto de la lógica
	    return "Pago/spago";
	}
	
	
	

	@GetMapping("/{idReserva}/tarjeta")
	public String Tarjeta( @PathVariable("idReserva") int idReserva, Model model) {
		Venta venta = new Venta();
	    Reserva reserva = serviceres.findById(idReserva);
	    model.addAttribute("venta", venta);
	    model.addAttribute("reserva", reserva);
	    return "Pago/mdptarjeta";
	}
	
	@GetMapping("/{idReserva}/efectivo")
	public String Efectivo( @PathVariable("idReserva") int idReserva, Model model) {
		Venta venta = new Venta();
	    Reserva reserva = serviceres.findById(idReserva);
	    model.addAttribute("venta", venta);
	    model.addAttribute("reserva", reserva);
	    return "Pago/mdppagoefectivo";
	}
	
	
	@GetMapping("/{idReserva}/yape")
	public String Yape( @PathVariable("idReserva") int idReserva, Model model) {
		Venta venta = new Venta();
	    Reserva reserva = serviceres.findById(idReserva);
	    
	    model.addAttribute("venta", venta);
	    model.addAttribute("reserva", reserva);
	    return "Pago/mdpyape";
	}
	
	
	
	
}
