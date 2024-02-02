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
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import static com.sicred.sistema.rest.mapper.AssociadoMapper.toDtoList;

@RestController
@RequestMapping(value="/api/associados",  produces = {"application/json"})
@Tag(name="Associado Controller")
public class AssociadoController {
	
	@Autowired
	private AssociadoService associadoService;
	
	@Operation(summary = "Realiza cadastro de associados", method = "POST")
	@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cadastro de associado realizado com sucesso."),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida."),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos."),
            @ApiResponse(responseCode = "500", description = "Erro ao cadastrar um associado.")
    })
	
	@PostMapping
    public ResponseEntity<AssociadoDTO> cadastrar(@RequestBody AssociadoDTO associadoDto) {
		Associado associadoCadastrado = associadoService.cadastrar(AssociadoMapper.toEntity(associadoDto));
        if(associadoCadastrado != null) {
        	return new ResponseEntity<>(AssociadoMapper.toDto(associadoCadastrado), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(null, HttpStatus.CONFLICT);
    }
	 @Operation(summary = "Recupera todos os associados", method = "GET")
	 @ApiResponses(value = {
	            @ApiResponse(responseCode = "200", description = "Busca de associados realizada com sucesso."),
	            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida."),
	            @ApiResponse(responseCode = "400", description = "Parametros inválidos."),
	            @ApiResponse(responseCode = "500", description = "Erro ao buscar associados.")
	 })
	 @GetMapping
	 public ResponseEntity<List<AssociadoDTO>>recuperarTodosAssociados(){
		 List<Associado>listaDeAssociados = associadoService.recuperarTodosAssociados();
		 return new ResponseEntity<>(toDtoList(listaDeAssociados), HttpStatus.OK);
	 }

}
