package idat.edu.pe.servicio;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idat.edu.pe.modelo.Alimentacion;
import idat.edu.pe.repositorio.AlimentacionRepository;

@Service
@Transactional
public class AlimentacionServiceImplement implements AlimentacionService {

	@Autowired
	private AlimentacionRepository repository;

	@Override
	public void insert(Alimentacion alimentacion) {
		// TODO Auto-generated method stub
		repository.save(alimentacion);
	}

	@Override
	public void update(Alimentacion alimentacion) {
		// TODO Auto-generated method stub
		repository.save(alimentacion);
	}

	@Override
	public void delete(Integer IdAlimentacion) {
		// TODO Auto-generated method stub
		repository.deleteById(IdAlimentacion);
	}

	@Override
	public Alimentacion findById(Integer IdAlimentacion) {
		// TODO Auto-generated method stub
		return repository.findById(IdAlimentacion).orElse(null);
	}

	@Override
	public Collection<Alimentacion> findAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

}
