package idat.edu.pe.servicio;

import java.nio.file.Path;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.multipart.MultipartFile;

import idat.edu.pe.modelo.Alimentacion;
import idat.edu.pe.modelo.Bus;
import idat.edu.pe.modelo.Guia;
import idat.edu.pe.modelo.Paquete;
import idat.edu.pe.modelo.ServicioHoteleria;

public interface PaqueteService {
	//el list es el de la busqueda
	public  List<Paquete> listAll(String palabraClave);
	public abstract void insert(Paquete paquete);
	public abstract void update(Paquete paquete);
	public abstract void delete(Integer IdPaquete);
	public abstract Paquete findById(Integer IdPaquete);
	public abstract Collection<Paquete> findAll();
	public List<ServicioHoteleria> listHoteleria();
	public List<Bus> listBus();
	public List<Guia> listGuia();
	public List<Alimentacion> listAlimentacion();
}
