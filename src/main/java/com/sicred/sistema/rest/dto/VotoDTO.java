package com.sicred.sistema.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VotoDTO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("id_pauta")
    private Long idPauta;

    @JsonProperty("id_cooperado")
    private Long idCooperado;

    @JsonProperty("cpf")
    private String cpf;

    @JsonProperty("voto")
    private String voto;
    
    @JsonProperty("nome_votante")
    private String nomeVotante;

    @Override
    public String toString() {
        return "VotoDTO{" +
                "idPauta=" + idPauta +
                ", cpf='" + cpf + '\'' +
                ", voto='" + voto + '\'' +
                '}';
    }
}

