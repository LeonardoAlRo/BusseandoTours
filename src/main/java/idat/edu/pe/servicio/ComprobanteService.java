package idat.edu.pe.servicio;

import java.util.Collection;
import java.util.List;

import idat.edu.pe.modelo.Comprobante;
import idat.edu.pe.modelo.Paquete;


public interface ComprobanteService {
	
	public List<Comprobante> listAll();
	public List<Comprobante> listAll(String palabraClave);
	public abstract void insert(Comprobante comprobante);
	public abstract void update(Comprobante comprobante);
	public abstract void delete(Integer IdComprobante);
	public abstract Comprobante findById(Integer IdComprobante);
	public abstract Collection<Comprobante> findAll();
}
