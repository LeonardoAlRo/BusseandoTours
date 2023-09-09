package idat.edu.pe.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import idat.edu.pe.modelo.Venta;
import idat.edu.pe.modelo.Paquete;

@Repository
public interface VentasRepositorio extends JpaRepository<Venta, Integer> {
	@Query("SELECT p FROM Venta p WHERE" + " CONCAT (p.FechaVenta)" + " LIKE %?1%")

	public List<Venta> findAll(String palabraClave);
}
