package idat.edu.pe.servicio;

import java.util.Collection;
import java.util.List;

import idat.edu.pe.modelo.ServicioHoteleria;

public interface ServicioHoteleriaService {

	public abstract void insert(ServicioHoteleria servicioHoteleria);

	public abstract void update(ServicioHoteleria servicioHoteleria);

	public abstract void delete(Integer idServicioHoteleria);

	public abstract ServicioHoteleria findById(Integer idServicioHoteleria);

	public abstract Collection<ServicioHoteleria> findAll();
	
	
	
	

}
