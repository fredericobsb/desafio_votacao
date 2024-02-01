package com.sicred.sistema.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sicred.sistema.entidades.Associado;
import com.sicred.sistema.repository.AssociadoRepository;
import com.sicred.sistema.validator.CpfValidador;
import com.sicred.sistema.validator.ValidaCPF;

@Service
public class AssociadoService {
	
	private static final Logger logger = LoggerFactory.getLogger(AssociadoService.class);
	
	@Autowired
	AssociadoRepository associadoRepository;
	
	//CpfValidador cpfValidador = new CpfValidador();
	ValidaCPF validaCpf = null;
	
	 public Associado cadastrar(Associado associado) {
		    Boolean isJaCadastrado = isJaCadastrado(associado.getCpf());
		    Boolean resultadoValidacaoCpf = null;
		    if(!isJaCadastrado) {
		    	resultadoValidacaoCpf = ValidaCPF.isCPF(associado.getCpf());
		        if(resultadoValidacaoCpf) {
		        	logger.info("cadastrando novo associado: " + associado);
			        return associadoRepository.save(associado);
		        }
		    }
		   
		   return null;
	 }
	 
	 public List<Associado>recuperarTodosAssociados(){
		 return associadoRepository.findAll();
	 }
	 
	 public Boolean isJaCadastrado(String cpf) {
		 Associado associado = associadoRepository.findByCpf(cpf);
		 if(associado != null) {
			 return true;
		 }
		return false;	
	 }

}
