package idat.edu.pe.servicio;

import java.util.Collection;
import java.util.List;

import idat.edu.pe.modelo.Reserva;
import idat.edu.pe.modelo.Paquete;


public interface ReservasService {
	
	public List<Reserva> listAll();
	public List<Reserva> listAll(String palabraClave);
	public abstract void insert(Reserva reserva);
	public abstract void update(Reserva reserva);
	public abstract void delete(Integer IdReserva);
	public abstract Reserva findById(Integer IdReserva);
	public abstract Collection<Reserva> findAll();
}
