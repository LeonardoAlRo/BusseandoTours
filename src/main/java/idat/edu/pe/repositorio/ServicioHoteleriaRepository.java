package idat.edu.pe.repositorio;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import idat.edu.pe.modelo.ServicioHoteleria;



@Repository
public interface ServicioHoteleriaRepository extends JpaRepository<ServicioHoteleria, Integer>{

}
