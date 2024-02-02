package com.sicred.sistema.entidades;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VotoPK implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long idPauta;
    private Long idCooperado;
}