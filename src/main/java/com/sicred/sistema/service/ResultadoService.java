package com.sicred.sistema.service;

import static com.sicred.sistema.shared.Constantes.EMPATE;
import static com.sicred.sistema.shared.Constantes.NAO;
import static com.sicred.sistema.shared.Constantes.SIM;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sicred.sistema.entidades.Pauta;
import com.sicred.sistema.entidades.Voto;
import com.sicred.sistema.repository.PautaRepository;
import com.sicred.sistema.rest.dto.ResultadoDTO;

@Service
public class ResultadoService {

	@Autowired
    private PautaService pautaService;
	
	private static final Logger logger = LoggerFactory.getLogger(ResultadoService.class);
	
	
    public ResultadoDTO obterResultado(Long id) {
        logger.info("obtendo resultado de pauta numero: " + id);
        Pauta pauta = pautaService.buscarPorId(id);
        return construirResultado(pauta);
    }
    
    private ResultadoDTO construirResultado(Pauta pauta) {
        Integer quantidadeSim = obterQuantidadePorOpcao(pauta.getVotos(), SIM);
        Integer quantidadeNao = obterQuantidadePorOpcao(pauta.getVotos(), NAO);

        return ResultadoDTO.builder()
                .seqPauta(pauta.getId())
                .titulo(pauta.getTitulo())
                .quantidadeNao(quantidadeNao)
                .quantidadeSim(quantidadeSim)
                .status(pauta.getStatus())
                .resultado(calcularVotos(quantidadeSim, quantidadeNao))
                .build();
    }
    
    private Integer obterQuantidadePorOpcao(Set<Voto> votos, String opcao) {
        List<Voto> votosFiltrados = votos.stream().filter(voto -> opcao.equals(voto.getVoto())).collect(Collectors.toList());
        return votosFiltrados.size();
    }
    
    private String calcularVotos(Integer quantidadeSim, Integer quantidadeNao) {
        if (quantidadeNao.equals(quantidadeSim)) {
            return EMPATE;
        } else if (quantidadeNao > quantidadeSim) {
            return NAO;
        } else {
            return SIM;
        }
    }
}
