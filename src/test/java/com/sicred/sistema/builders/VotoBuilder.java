package com.sicred.sistema.builders;

import com.sicred.sistema.entidades.*;
import static com.sicred.sistema.shared.Constantes.SIM;

public class VotoBuilder {

    public static Voto umVoto() {
        return Voto.builder()
                .cpf("10338927425")
                .idPauta(1L)
                .voto(SIM)
                .build();
    }

    public static Voto umVoto(String voto) {
        return Voto.builder()
                .cpf("10338927425")
                .idPauta(1L)
                .voto(voto)
                .build();
    }
}
