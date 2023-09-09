package idat.edu.pe.servicio;

import java.util.Collection;
import java.util.List;

import idat.edu.pe.modelo.Venta;
import idat.edu.pe.modelo.Paquete;


public interface VentasService {
	
	public List<Venta> listAll();
	public List<Venta> listAll(String palabraClave);
	public abstract void insert(Venta venta);
	public abstract void update(Venta venta);
	public abstract void delete(Integer IdVentas);
	public abstract Venta findById(Integer IdVentas);
	public abstract Collection<Venta> findAll();
}
