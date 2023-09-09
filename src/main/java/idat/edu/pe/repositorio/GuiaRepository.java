package idat.edu.pe.repositorio;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import idat.edu.pe.modelo.Guia;



@Repository
public interface GuiaRepository extends JpaRepository<Guia, Integer>{

}
