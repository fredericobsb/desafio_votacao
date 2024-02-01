package com.sicred.sistema.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sicred.sistema.entidades.Associado;
import com.sicred.sistema.rest.dto.AssociadoDTO;
import com.sicred.sistema.rest.mapper.AssociadoMapper;
import com.sicred.sistema.service.AssociadoService;
import static com.sicred.sistema.rest.mapper.AssociadoMapper.toDtoList;

@RestController
@RequestMapping("/api/associados")
public class AssociadoController {
	
	@Autowired
	private AssociadoService associadoService;
	
	@PostMapping
    public ResponseEntity<AssociadoDTO> cadastrar(@RequestBody AssociadoDTO associadoDto) {
		Associado associadoCadastrado = associadoService.cadastrar(AssociadoMapper.toEntity(associadoDto));
        if(associadoCadastrado != null) {
        	return new ResponseEntity<>(AssociadoMapper.toDto(associadoCadastrado), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(null, HttpStatus.CONFLICT);
    }
	
	 @GetMapping
	 public ResponseEntity<List<AssociadoDTO>>recuperarTodosAssociados(){
		 List<Associado>listaDeAssociados = associadoService.recuperarTodosAssociados();
		 return new ResponseEntity<>(toDtoList(listaDeAssociados), HttpStatus.OK);
	 }

}
