package com.sicred.sistema.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.sicred.sistema.entidades.Pauta;

public interface PautaRepository extends JpaRepository<Pauta, Long>{
	
	  List<Pauta> findAllByStatus(String aberta);
}
