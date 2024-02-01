package com.sicred.sistema.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sicred.sistema.entidades.Pauta;
import com.sicred.sistema.entidades.User;
import com.sicred.sistema.rest.dto.PautaDTO;
import com.sicred.sistema.rest.mapper.PautaMapper;
import com.sicred.sistema.service.PautaService;
import com.sicred.sistema.service.UserService;

import static com.sicred.sistema.rest.mapper.PautaMapper.toDto;
import static com.sicred.sistema.rest.mapper.PautaMapper.toDtoList;

import java.util.List;

@RestController
@RequestMapping("/api/pautas")
public class PautaController {
	
	@Autowired
    private PautaService pautaService;

	 @PostMapping//(headers = "Api-Version=1")
	 public ResponseEntity<Object> cadastrar(@RequestBody PautaDTO pautaDTO) {
	     Pauta pautaCadastrada = pautaService.cadastrar(PautaMapper.toEntity(pautaDTO));
	     return new ResponseEntity<>(toDto(pautaCadastrada), HttpStatus.CREATED);
	 }
	 
	 @GetMapping
	 public ResponseEntity<List<PautaDTO>>recuperarTodasPautas(){
		 List<Pauta>listaDePautas = pautaService.recuperarTodas();
		 return new ResponseEntity<>(toDtoList(listaDePautas), HttpStatus.OK);
	 }
}
