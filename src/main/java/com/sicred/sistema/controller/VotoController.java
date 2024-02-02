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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;




@RestController
@RequestMapping(value = "/api/votos",  produces = {"application/json"})
@Tag(name="Voto Controller")
public class VotoController {
	
	@Autowired
	private VotoService votoService;
	
	@PostMapping
	@Operation(summary = "Cadastra um voto numa pauta", method = "POST")
	@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Voto cadastrado com sucesso."),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida."),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos."),
            @ApiResponse(responseCode = "500", description = "Erro ao cadastrar um voto.")
    })
    public ResponseEntity<Object> cadastrar(@RequestBody VotoDTO votoDTO) {
        Voto votoCadastrado = votoService.cadastrar(VotoMapper.toEntity(votoDTO));
        if(votoCadastrado != null) {
        	return new ResponseEntity<>(VotoMapper.toDto(votoCadastrado), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(null, HttpStatus.CONFLICT);
    }

}
