package com.sicred.sistema.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sicred.sistema.entidades.Pauta;
import com.sicred.sistema.rest.dto.PautaDTO;
import com.sicred.sistema.rest.dto.SessaoDTO;
import com.sicred.sistema.rest.mapper.PautaMapper;
import com.sicred.sistema.service.PautaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import static com.sicred.sistema.rest.mapper.PautaMapper.toDto;
import static com.sicred.sistema.rest.mapper.PautaMapper.toDtoList;

import java.util.List;


@RestController
@RequestMapping("/api/v1/pautas")
@Tag(name="Pauta Controller")
public class PautaController {
	
	@Autowired
    private PautaService pautaService;

	@Operation(summary = "Realiza cadastro de pautas", method = "POST")
	@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cadastro de pauta realizado com sucesso."),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida."),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos."),
            @ApiResponse(responseCode = "500", description = "Erro ao cadastrar uma pauta.")
    })
	 @PostMapping
	 public ResponseEntity<Object> cadastrar(@RequestBody PautaDTO pautaDTO) {
	     Pauta pautaCadastrada = pautaService.cadastrar(PautaMapper.toEntity(pautaDTO));
	     return new ResponseEntity<>(toDto(pautaCadastrada), HttpStatus.CREATED);
	 }
	 
	@Operation(summary = "Realiza busca de pautas", method = "GET")
	@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca de pautas realizada com sucesso."),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida."),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos."),
            @ApiResponse(responseCode = "500", description = "Erro ao buscar as pautas.")
    })
	 @GetMapping
	 public ResponseEntity<List<PautaDTO>>recuperarTodasPautas(){
		 List<Pauta>listaDePautas = pautaService.recuperarTodas();
		 return new ResponseEntity<>(toDtoList(listaDePautas), HttpStatus.OK);
	 }
	
	@Operation(summary = "Abrir sessão de votação em uma Pauta", method = "POST")
	@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sessão de votação aberta com sucesso."),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida."),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos."),
            @ApiResponse(responseCode = "500", description = "Erro ao cadastrar uma seção de votação.")
    })
	@PostMapping(value = "/abrir")
    public ResponseEntity<Object> abrirVotacao(@RequestBody SessaoDTO sessaoDTO) {
        Pauta pautaAberta = pautaService.abrirVotacao(sessaoDTO);
        return new ResponseEntity<>(toDto(pautaAberta), HttpStatus.ACCEPTED);
    }
}
