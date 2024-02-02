package com.sicred.sistema.builders;

import com.sicred.sistema.entidades.Pauta;

import java.time.LocalDateTime;
import java.util.List;

import static com.sicred.sistema.builders.VotoBuilder.umVoto;
import static com.sicred.sistema.shared.Constantes.*;
import static java.util.Set.of;

public class PautaBuilder {
    public static Pauta umaPautaFechada() {
        return Pauta.builder()
                .id(1L)
                .titulo("coxinha > all")
                .tempoLimite(LocalDateTime.now())
                .status(FECHADA).build();
    }

    public static Pauta umaPautaFechadaPorTempo() {
        return Pauta.builder()
                .id(1L)
                .titulo("coxinha > all")
                .tempoLimite(LocalDateTime.now().minusMinutes(1))
                .status(ABERTA).build();
    }

    public static Pauta umaPautaFechadaIhNaoEnviada() {
        return Pauta.builder()
                .status(FECHADA)
                .build();
    }

    public static Pauta umaPautaSemStatus() {
        return Pauta.builder()
                .titulo("coxinha > all")
                .build();
    }

    public static Pauta umaPautaAberta() {
        return Pauta.builder()
                .id(1L)
                .titulo("coxinha > all")
                .status(ABERTA)
                .tempoLimite(LocalDateTime.now().plusMinutes(2))
                .build();
    }

    public static List<Pauta> umaListaDePautas() {
        return List.of(umaPautaAberta());
    }

    public static Pauta umaPautaComMaisVotosSim() {
        Pauta pauta = Pauta.builder()
                .id(1L)
                .titulo("coxinha > all")
                .votos(of(umVoto(SIM), umVoto(SIM), umVoto(SIM), umVoto(NAO), umVoto(NAO)))
                .tempoLimite(LocalDateTime.now().minusMinutes(1))
                .status(FECHADA)
                .build();
        return pauta;
    }

    public static Pauta umaPautaEmpatada() {
        return Pauta.builder()
                .id(1L)
                .titulo("coxinha > all")
                .votos(of(umVoto(SIM), umVoto(SIM), umVoto(SIM), umVoto(NAO), umVoto(NAO), umVoto(NAO)))
                .tempoLimite(LocalDateTime.now().minusMinutes(1))
                .status(FECHADA)
                .build();
    }

    public static Pauta umaPautaComMaisVotosNao() {
        return Pauta.builder()
                .id(1L)
                .titulo("coxinha > all")
                .votos(of(umVoto(SIM), umVoto(SIM), umVoto(NAO), umVoto(NAO), umVoto(NAO)))
                .tempoLimite(LocalDateTime.now().minusMinutes(1))
                .status(FECHADA)
                .build();
    }

}