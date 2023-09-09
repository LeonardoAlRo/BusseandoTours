package idat.edu.pe.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import idat.edu.pe.modelo.Reserva;
import idat.edu.pe.modelo.Paquete;

@Repository
public interface ReservasRepositorio extends JpaRepository<Reserva, Integer> {
	@Query("SELECT p FROM Reserva p WHERE" + " CONCAT (p.FechaReserva)" + " LIKE %?1%")
	
	public List<Reserva> findAll(String palabraClave);
}
