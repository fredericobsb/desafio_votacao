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

@RestController
@RequestMapping("/api/resultado")
public class ResultadoController {

	@Autowired
	private ResultadoService resultadoService;
	
	@GetMapping(value = "/{id}")//, headers = "Api-Version=1"
    public ResponseEntity<Object> obterResultado(@PathVariable Long id) {
        ResultadoDTO resultadoDTO = resultadoService.obterResultado(id);
        return new ResponseEntity<>(resultadoDTO, HttpStatus.OK);
    }
}
