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

import idat.edu.pe.modelo.Reserva;
import idat.edu.pe.modelo.Comprobante;
import idat.edu.pe.modelo.Paquete;
import idat.edu.pe.modelo.Pasajero;
import idat.edu.pe.modelo.Usuario;
import idat.edu.pe.modelo.Venta;
import idat.edu.pe.repositorio.UsuarioRepositorio;
import idat.edu.pe.repositorio.VentasRepositorio;
import idat.edu.pe.servicio.ComprobanteService;
import idat.edu.pe.servicio.PaqueteService;
import idat.edu.pe.servicio.PasajeroService;
import idat.edu.pe.servicio.ReservasService;
import idat.edu.pe.servicio.UsuarioServicio;
import idat.edu.pe.servicio.VentasService;
import idat.edu.pe.util.reportes.VentaExporterExcel;
import idat.edu.pe.util.reportes.VentaExporterPDF;


@Controller
@RequestMapping("/comprobante")
public class ComprobanteController {
	
	@Autowired(required = false)
	private VentasService serviceven;
	
	@Autowired(required = false)
	private ReservasService serviceres;
	
	@Autowired(required = false)
	private ComprobanteService service;
	
	@Autowired(required = false)
	private UsuarioServicio servicesus;

	@GetMapping("/listar")
	public String verPaginaDeComprobantes(Model modelo, @Param("palabraClave")String palabraClave) {
		modelo.addAttribute("comprobante", service.listAll(palabraClave));
		return "Comprobante/Comprobantes";
	}
	
	
	@GetMapping("/frmComprobante/{idVentas}")
	public String verComprobante(Model modelo, @PathVariable("idVentas") int idVentas, RedirectAttributes attribute, HttpServletRequest request) {
	    Venta venta = serviceven.findById(idVentas);
	    Comprobante comprobante = new Comprobante();
	    
	    if (venta == null) {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Venta no encontrado");
	    }else if(venta.getDocumentoGenerado() == true) {
	    	attribute.addFlashAttribute("success", "Ya tiene un comprobante generado!!");
	    	String referer = request.getHeader("Referer");
		    return "redirect:" + referer;
	    }
	    	
		     modelo.addAttribute("venta", venta);
		     modelo.addAttribute("comprobante", comprobante);
	         return "Comprobante/frmComprobante";
	       
	}
	
	
	@PostMapping("/save")
	public String guardarComprobante(@ModelAttribute("comprobante") @Valid Comprobante comprobante, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
	        // Si hay errores de validación, se vuelve a mostrar el formulario con los errores correspondientes
			return "Comprobante/frmComprobante";
	    } else {
	       
	        service.insert(comprobante);
	        Venta venta = comprobante.getVenta();
	        venta.setDocumentoGenerado(true);
	        serviceven.update(venta);
	        redirectAttributes.addFlashAttribute("success", "Comprobante registrado, no olvidar GENERAR  y enviar al cliente!!!");
	        return "redirect:/";
	        
	    }
	}

	
	
}
