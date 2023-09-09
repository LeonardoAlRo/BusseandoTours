package idat.edu.pe.servicio;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idat.edu.pe.modelo.Venta;
import idat.edu.pe.modelo.Paquete;
import idat.edu.pe.modelo.Pasajero;
import idat.edu.pe.repositorio.PasajeroRepository;
import idat.edu.pe.repositorio.VentasRepositorio;

@Service
@Transactional
public class VentasServiceImplement implements VentasService{

	@Autowired
	private VentasRepositorio repository;
	
	@Override
	public void insert(Venta venta) {
		// TODO Auto-generated method stub
		repository.save(venta);
	}

	@Override
	public void update(Venta venta) {
		// TODO Auto-generated method stub
		repository.save(venta);
	}

	@Override
	public void delete(Integer IdVentas) {
		// TODO Auto-generated method stub
		repository.deleteById(IdVentas);
	}

	@Override
	public Venta findById(Integer IdVentas) {
		// TODO Auto-generated method stub
		return repository.findById(IdVentas).orElse(null);
	}

	@Override
	public Collection<Venta> findAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public List<Venta> listAll() {
		// TODO Auto-generated method stub
		return (List<Venta>) repository.findAll() ;
	}

	@Override
	public List<Venta> listAll(String palabraClave) {
		if(palabraClave != null) {
			return repository.findAll(palabraClave);
		}
		return repository.findAll();
	}

	
	 
}
