package com.sicred.sistema.rest.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SessaoDTO implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("id_pauta")
    private Long idPauta;

    @JsonProperty("minutos")
    private Integer minutos;

    @Override
    public String toString() {
        return "SessaoDTO{" +
                "idPauta=" + idPauta +
                ", minutos=" + minutos +
                '}';
    }
}
