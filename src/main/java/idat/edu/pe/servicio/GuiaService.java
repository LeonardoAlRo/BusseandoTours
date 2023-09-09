package idat.edu.pe.servicio;

import java.util.Collection;

import idat.edu.pe.modelo.Guia;

public interface GuiaService {

	public abstract void insert(Guia guia);
	public abstract void update(Guia guia);
	public abstract void delete(Integer idGuiaTuristico);
	public abstract Guia findById(Integer idGuiaTuristico);
	public abstract Collection<Guia> findAll();
	
	
}
