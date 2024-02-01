package com.sicred.sistema.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sicred.sistema.entidades.Associado;

public interface AssociadoRepository extends JpaRepository<Associado, Long>{

	Associado findByCpf(String cpf);
}
