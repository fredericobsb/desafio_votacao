package com.sicred.sistema.rest.mapper;

import com.sicred.sistema.entidades.Voto;
import com.sicred.sistema.rest.dto.VotoDTO;

public class VotoMapper {
    public static Voto toEntity(VotoDTO votoDTO) {
        return Voto.builder()
                .cpf(votoDTO.getCpf())
                .idPauta(votoDTO.getIdPauta())
                .idCooperado(votoDTO.getIdCooperado())
                .voto(votoDTO.getVoto())
                .nomeVotante(votoDTO.getNomeVotante())
                .build();
    }

    public static VotoDTO toDto(Voto voto) {
        return VotoDTO.builder()
                .cpf(voto.getCpf())
                .idPauta(voto.getIdPauta())
                .idCooperado(voto.getIdCooperado())
                .voto(voto.getVoto())
                .nomeVotante(voto.getNomeVotante())
                .build();
    }
}

