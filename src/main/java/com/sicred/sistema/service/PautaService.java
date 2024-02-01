package com.sicred.sistema.service;

import static com.sicred.sistema.shared.Constantes.ABERTA;
import static com.sicred.sistema.shared.Constantes.PAUTA_NAO_ENCONTRADA_EXCEPTION;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sicred.sistema.entidades.Pauta;
import com.sicred.sistema.repository.PautaRepository;
import com.sicred.sistema.rest.exception.PautaNaoEncontradaException;

@Service
public class PautaService {

	@Autowired
    private PautaRepository pautaRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(PautaService.class);
	
    public Pauta cadastrar(Pauta pauta) {
        pauta.obterStatusFechadaCasoNulo(pauta);
        logger.info("salvando pauta: " + pauta);
        return pautaRepository.save(pauta);
    }
    
   
    public Pauta buscarPorId(Long id) {
        logger.info("abrindo nova Pauta por id: " + id);
        return pautaRepository.findById(id).orElseThrow(() -> {
            throw new PautaNaoEncontradaException(PAUTA_NAO_ENCONTRADA_EXCEPTION);
        });
    }
    
    public List<Pauta> consultarPautasAbertas() {
        logger.info("Consultando pautas com status aberto");
        return pautaRepository.findAllByStatus(ABERTA);
    }
    
    public List<Pauta> recuperarTodas() {
        logger.info("Buscando todas as pautas");
        return pautaRepository.findAll();
    }
    
    public Pauta atualizarPauta(Pauta pauta) {
        logger.info("atualizando pauta: " + pauta);
        return this.pautaRepository.save(pauta);
    }
}
