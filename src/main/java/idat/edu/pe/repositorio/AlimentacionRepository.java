package idat.edu.pe.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import idat.edu.pe.modelo.Alimentacion;


@Repository
public interface AlimentacionRepository extends JpaRepository<Alimentacion, Integer>{

}
