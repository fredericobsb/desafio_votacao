package com.sicred.sistema.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sicred.sistema.rest.dto.ResultadoDTO;
import com.sicred.sistema.service.ResultadoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value="/api/v1/resultado",  produces = {"application/json"})
@Tag(name="Resultado Controller")
public class ResultadoController {

	@Autowired
	private ResultadoService resultadoService;
	
	@Operation(summary = "Realiza busca de resultados das votações numa pauta", method = "GET")
	@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca de resultado realizada com sucesso."),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida."),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos."),
            @ApiResponse(responseCode = "500", description = "Erro ao buscar um resultado.")
    })
	@GetMapping(value = "/{id}")
    public ResponseEntity<Object> obterResultado(@PathVariable Long id) {
        ResultadoDTO resultadoDTO = resultadoService.obterResultado(id);
        return new ResponseEntity<>(resultadoDTO, HttpStatus.OK);
    }
}
