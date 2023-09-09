package idat.edu.pe.servicio;

import java.util.Collection;

import idat.edu.pe.modelo.Bus;

public interface BusService {

	public abstract void insert(Bus bus);
	public abstract void update(Bus bus);
	public abstract void delete(Integer idBus);
	public abstract Bus findById(Integer idBus);
	public abstract Collection<Bus> findAll();

}
