package com.sicred.sistema.rest.mapper;

import java.util.ArrayList;
import java.util.List;

import com.sicred.sistema.entidades.Pauta;
import com.sicred.sistema.rest.dto.PautaDTO;

public class PautaMapper {
    public static Pauta toEntity(PautaDTO pautaDTO) {
        return Pauta.builder()
                .id(pautaDTO.getId())
                .status(pautaDTO.getStatus())
                .tempoLimite(pautaDTO.getTempoLimite())
                .titulo(pautaDTO.getTitulo())
                .build();
    }

    public static PautaDTO toDto(Pauta pauta) {
        return PautaDTO.builder()
                .id(pauta.getId())
                .status(pauta.getStatus())
                .tempoLimite(pauta.getTempoLimite())
                .titulo(pauta.getTitulo())
                .build();
    }
    
    public static List<PautaDTO> toDtoList(List<Pauta> lista) {
    	List<PautaDTO>listaDto = new ArrayList<>();
    	PautaDTO dto = null;
    	for(Pauta p : lista) {
    		dto = PautaDTO.builder()
    				.id(p.getId())
    				.status(p.getStatus())
                    .tempoLimite(p.getTempoLimite())
                    .titulo(p.getTitulo())
                    .build();
    		listaDto.add(dto);
    	}
    	return listaDto;
    }
}

