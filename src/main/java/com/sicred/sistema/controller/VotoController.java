package com.sicred.sistema.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sicred.sistema.entidades.Voto;
import com.sicred.sistema.rest.dto.VotoDTO;
import com.sicred.sistema.rest.mapper.VotoMapper;
import com.sicred.sistema.service.VotoService;

@RestController
@RequestMapping("/api/votos")
public class VotoController {
	
	@Autowired
	private VotoService votoService;
	
	@PostMapping//(headers = "Api-Version=1")
    public ResponseEntity<Object> cadastrar(@RequestBody VotoDTO votoDTO) {
        Voto votoCadastrado = votoService.cadastrar(VotoMapper.toEntity(votoDTO));
        if(votoCadastrado != null) {
        	return new ResponseEntity<>(VotoMapper.toDto(votoCadastrado), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(null, HttpStatus.CONFLICT);
    }

}
