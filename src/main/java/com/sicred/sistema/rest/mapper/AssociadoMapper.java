package com.sicred.sistema.rest.mapper;

import java.util.ArrayList;
import java.util.List;

import com.sicred.sistema.entidades.Associado;
import com.sicred.sistema.entidades.Pauta;
import com.sicred.sistema.rest.dto.AssociadoDTO;
import com.sicred.sistema.rest.dto.PautaDTO;

public class AssociadoMapper {

	public static Associado toEntity(AssociadoDTO associadoDTO) {
        return Associado.builder()
                .id(associadoDTO.getId())
                .nome(associadoDTO.getNome())
                .cpf(associadoDTO.getCpf())
                .build();
    }

    public static AssociadoDTO toDto(Associado associado) {
        return AssociadoDTO.builder()
                .id(associado.getId())
                .nome(associado.getNome())
                .cpf(associado.getCpf())
                .build();
    }
    
    public static List<AssociadoDTO> toDtoList(List<Associado> lista) {
    	List<AssociadoDTO>listaDto = new ArrayList<>();
    	AssociadoDTO dto = null;
    	for(Associado a : lista) {
    		dto = AssociadoDTO.builder()
    				.id(a.getId())
    				.nome(a.getNome())
                    .cpf(a.getCpf())
                    .build();
    		listaDto.add(dto);
    	}
    	return listaDto;
    }
}
