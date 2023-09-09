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
import idat.edu.pe.modelo.Pasajero;
import idat.edu.pe.modelo.Usuario;
import idat.edu.pe.modelo.Venta;
import idat.edu.pe.repositorio.UsuarioRepositorio;
import idat.edu.pe.repositorio.VentasRepositorio;
import idat.edu.pe.servicio.PaqueteService;
import idat.edu.pe.servicio.PasajeroService;
import idat.edu.pe.servicio.ReservasService;
import idat.edu.pe.servicio.UsuarioServicio;
import idat.edu.pe.servicio.VentasService;
import idat.edu.pe.util.reportes.VentaExporterExcel;
import idat.edu.pe.util.reportes.VentaExporterPDF;


@Controller
@RequestMapping("/ventas")
public class VentasController {
	
	@Autowired(required = false)
	private VentasService service;
	
	@Autowired(required = false)
	private ReservasService serviceres;
	
	@Autowired(required = false)
	private PasajeroService servicepas;
	
	@Autowired(required = false)
	private UsuarioServicio servicesus;

	@GetMapping("/listar")
	public String verPaginaDeVentas(Model modelo, @Param("palabraClave")String palabraClave) {
		modelo.addAttribute("ventas", service.listAll(palabraClave));
		return "Venta/ventas";
	}
	
	
	@GetMapping("/listarcompra/{id}")
	public String listaCompras(Model modelo,  @PathVariable("id") Long id) {
		Optional<Usuario> usuario = servicesus.findById(id);
	    if (usuario.isPresent()) {
	        List<Venta> ventas = usuario.get().getVentas();
	        modelo.addAttribute("ventas", ventas);
	        return "Venta/Compras";
	    } else {
	        return "";
	    }
	}
	
	
	

		@PostMapping("/save")
		public String guardarVenta(@ModelAttribute("venta") @Valid Venta venta, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
		    if (result.hasErrors()) {
		        // Si hay errores de validación, se vuelve a mostrar el formulario con los errores correspondientes
		        return "Error";
		    } else {
		       
		        service.insert(venta);
		        Reserva reserva = venta.getReserva();
		        reserva.setEstado("PAGADO");
		        serviceres.update(reserva);
		        
		        redirectAttributes.addFlashAttribute("success", "¡Gracias por su compra! Estamos procesando su pedido y le enviaremos su Comprobante de venta en el transcurso de la próxima semana. ¡Esperamos que disfrute de su viaje!");
		        return "redirect:/";
		        
		    }
		}
		
		
		
		
		
		
		//la exportación de las ventas son con la fecha actual que se descarga y el Componente de Venta
		@GetMapping("/exportarPDF")
		public void exportarListadoDeVentasEnPDF(HttpServletResponse response) throws DocumentException, IOException {
			response.setContentType("application/pdf");
			
			DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
			String fechaActual = dateFormatter.format(new Date());
			
			String cabecera = "Content-Disposition";
			String palabraClave = "attachment; filename=Ventas_" + fechaActual + ".pdf";
			
			response.setHeader(cabecera, palabraClave);
			
			List<Venta> ventas = service.listAll();

			
			VentaExporterPDF exporter = new VentaExporterPDF(ventas);
			exporter.exportar(response);
			
		}
		
		
		//la exportación de las ventas en EXCEL
			@GetMapping("/exportarExcel")
			public void exportarListadoDeVentasEnExcel(HttpServletResponse response) throws DocumentException, IOException {
				response.setContentType("application/octet-steam");
				
				DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
				String fechaActual = dateFormatter.format(new Date());
				
				String cabecera = "Content-Disposition";
				String palabraClave = "attachment; filename=Ventas_" + fechaActual + ".xlsx";
				
				response.setHeader(cabecera, palabraClave);
				
				List<Venta> ventas = service.listAll();

				
				VentaExporterExcel exporter = new VentaExporterExcel(ventas);
				exporter.exportar(response);
				
			}
			
		
			
			
			
			
			
			
			
			
			
}
