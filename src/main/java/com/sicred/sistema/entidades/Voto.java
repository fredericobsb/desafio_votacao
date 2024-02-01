package com.sicred.sistema.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@jakarta.persistence.IdClass(VotoPK.class)
@Entity(name = "VOTO")
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"ID_COOPERADO", "ID_PAUTA"})})
public class Voto {

    @Id
    @Column(name = "ID_PAUTA")
    private Long idPauta;

    @Id
    @Column(name = "ID_COOPERADO")
    private Long idCooperado;

    @Column(name = "CPF")
    private String cpf;

    @Column(name = "VOTO")
    private String voto;
    
    @Column(name = "NOME_VOTANTE")
    private String nomeVotante;
}

