package idat.edu.pe.servicio;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idat.edu.pe.modelo.Bus;
import idat.edu.pe.repositorio.BusRepository;

@Service
@Transactional
public class BusServiceImplement implements BusService {

	@Autowired
	private BusRepository repository;
	
	@Override
	public void insert(Bus bus) {
		// TODO Auto-generated method stub
		repository.save(bus);
	}

	@Override
	public void update(Bus bus) {
		// TODO Auto-generated method stub
		repository.save(bus);
	}

	@Override
	public void delete(Integer idBus) {
		// TODO Auto-generated method stub
		repository.deleteById(idBus);
	}

	@Override
	public Bus findById(Integer idBus) {
		// TODO Auto-generated method stub
		return repository.findById(idBus).orElse(null);
	}

	@Override
	public Collection<Bus> findAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}



}
