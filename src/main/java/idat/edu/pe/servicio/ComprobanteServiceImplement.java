package idat.edu.pe.servicio;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idat.edu.pe.modelo.Comprobante;
import idat.edu.pe.modelo.Paquete;
import idat.edu.pe.modelo.Pasajero;
import idat.edu.pe.modelo.Reserva;
import idat.edu.pe.repositorio.PasajeroRepository;
import idat.edu.pe.repositorio.ComprobanteRepositorio;

@Service
@Transactional
public class ComprobanteServiceImplement implements ComprobanteService{

	@Autowired
	private ComprobanteRepositorio repository;
	
	@Override
	public List<Comprobante> listAll() {
		// TODO Auto-generated method stub
		return (List<Comprobante>) repository.findAll();
	}

	@Override
	public List<Comprobante> listAll(String palabraClave) {
		// TODO Auto-generated method stub
		if(palabraClave != null) {
			return repository.findAll(palabraClave);
		}
		return repository.findAll();
	}

	@Override
	public void insert(Comprobante comprobante) {
		// TODO Auto-generated method stub
		repository.save(comprobante);
	}

	@Override
	public void update(Comprobante comprobante) {
		// TODO Auto-generated method stub
		repository.save(comprobante);
	}

	@Override
	public void delete(Integer IdComprobante) {
		// TODO Auto-generated method stub
		repository.deleteById(IdComprobante);
	}

	@Override
	public Comprobante findById(Integer IdComprobante) {
		// TODO Auto-generated method stub
		return repository.findById(IdComprobante).orElse(null);
	}

	@Override
	public Collection<Comprobante> findAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	
	
	 
}
