package idat.edu.pe.servicio;

import java.util.Collection;

import idat.edu.pe.modelo.Alimentacion;

public interface AlimentacionService {
	
	public abstract void insert(Alimentacion alimentacion);
	public abstract void update(Alimentacion alimentacion);
	public abstract void delete(Integer IdAlimentacion);
	public abstract Alimentacion findById(Integer IdAlimentacion);
	public abstract Collection<Alimentacion> findAll();

	
}
