package idat.edu.pe.servicio;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idat.edu.pe.modelo.Guia;
import idat.edu.pe.repositorio.GuiaRepository;

@Service
@Transactional
public class GuiaServiceImplement implements GuiaService {

	@Autowired
	private GuiaRepository repository;
	
	@Override
	public void insert(Guia guia) {
		// TODO Auto-generated method stub
		repository.save(guia);
	}

	@Override
	public void update(Guia guia) {
		// TODO Auto-generated method stub
		repository.save(guia);
	}

	@Override
	public void delete(Integer idGuiaTuristico) {
		// TODO Auto-generated method stub
		repository.deleteById(idGuiaTuristico);
	}

	@Override
	public Guia findById(Integer idGuiaTuristico) {
		// TODO Auto-generated method stub
		return repository.findById(idGuiaTuristico).orElse(null);
	}

	@Override
	public Collection<Guia> findAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	

}
