package com.sicred.sistema.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sicred.sistema.entidades.Voto;
import com.sicred.sistema.entidades.VotoPK;

public interface VotoRepository extends JpaRepository<Voto, VotoPK> {

	Voto findByCpf(String cpf);
}
