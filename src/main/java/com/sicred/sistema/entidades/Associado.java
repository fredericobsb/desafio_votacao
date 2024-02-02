package com.sicred.sistema.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "ASSOCIADO")
public class Associado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ASSOCIADO")
	private Long id;
	
	@Column(name = "NOME_ASSOCIADO")
	private String nome;
	
	@Column(name = "CPF_ASSOCIADO")
	private String cpf;
}
