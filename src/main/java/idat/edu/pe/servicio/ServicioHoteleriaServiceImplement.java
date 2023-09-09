package idat.edu.pe.servicio;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idat.edu.pe.modelo.ServicioHoteleria;
import idat.edu.pe.repositorio.ServicioHoteleriaRepository;

@Service
@Transactional
public class ServicioHoteleriaServiceImplement implements ServicioHoteleriaService{

	@Autowired
	private ServicioHoteleriaRepository repository;
	
	@Override
	public void insert(ServicioHoteleria servicioHoteleria) {
		// TODO Auto-generated method stub
		repository.save(servicioHoteleria);
	}

	@Override
	public void update(ServicioHoteleria servicioHoteleria) {
		// TODO Auto-generated method stub
		repository.save(servicioHoteleria);
	}

	@Override
	public void delete(Integer idServicioHoteleria) {
		// TODO Auto-generated method stub
		repository.deleteById(idServicioHoteleria);
	}

	@Override
	public ServicioHoteleria findById(Integer idServicioHoteleria) {
		// TODO Auto-generated method stub
		return repository.findById(idServicioHoteleria).orElse(null);
	}

	@Override
	public Collection<ServicioHoteleria> findAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	

}
