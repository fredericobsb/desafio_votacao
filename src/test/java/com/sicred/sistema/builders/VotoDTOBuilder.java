package com.sicred.sistema.builders;

import com.sicred.sistema.rest.dto.VotoDTO;
import static com.sicred.sistema.shared.Constantes.SIM;

public class VotoDTOBuilder {

    public static VotoDTO umVotoDTO() {
        return VotoDTO.builder()
                .cpf("10338927425")
                .idCooperado(1L)
                .idPauta(1L)
                .voto(SIM)
                .build();
    }

    public static VotoDTO umVotoDTO(String voto) {
        return VotoDTO.builder()
                .cpf("10338927425")
                .idCooperado(1L)
                .idPauta(1L)
                .voto(voto)
                .build();
    }
}
