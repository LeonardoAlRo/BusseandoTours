package idat.edu.pe.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import idat.edu.pe.modelo.Bus;

@Repository
public interface BusRepository extends JpaRepository<Bus, Integer>{

}
