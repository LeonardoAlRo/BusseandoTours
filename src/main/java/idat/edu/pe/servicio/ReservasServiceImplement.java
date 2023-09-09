package idat.edu.pe.servicio;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idat.edu.pe.modelo.Reserva;
import idat.edu.pe.modelo.Paquete;
import idat.edu.pe.modelo.Pasajero;
import idat.edu.pe.repositorio.PasajeroRepository;
import idat.edu.pe.repositorio.ReservasRepositorio;

@Service
@Transactional
public class ReservasServiceImplement implements ReservasService{
	
	@Autowired
	private ReservasRepositorio repository;
	
	@Override
	public List<Reserva> listAll() {
		// TODO Auto-generated method stub
		return (List<Reserva>) repository.findAll();
	}

	@Override
	public List<Reserva> listAll(String palabraClave) {
		// TODO Auto-generated method stub
		if(palabraClave != null) {
			return repository.findAll(palabraClave);
		}
		return repository.findAll();
	}

	@Override
	public void insert(Reserva reserva) {
		// TODO Auto-generated method stub
		repository.save(reserva);
	}

	@Override
	public void update(Reserva reserva) {
		// TODO Auto-generated method stub
		repository.save(reserva);
	}

	@Override
	public void delete(Integer IdReserva) {
		// TODO Auto-generated method stub
		repository.deleteById(IdReserva);
	}

	@Override
	public Reserva findById(Integer IdReserva) {
		// TODO Auto-generated method stub
		return repository.findById(IdReserva).orElse(null);
	}

	@Override
	public Collection<Reserva> findAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
		
	} 


	
	 
}
