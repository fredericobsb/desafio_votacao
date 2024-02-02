package com.sicred.sistema.service;

import static com.sicred.sistema.shared.Constantes.SESSAO_FECHADA_EXCEPTION;
import static com.sicred.sistema.shared.Constantes.NAO;
import static com.sicred.sistema.shared.Constantes.SIM;
import static com.sicred.sistema.shared.Constantes.VOTO_DUPLICADO_EXCEPTION;
import static com.sicred.sistema.shared.Constantes.VOTO_INVALIDO_EXCEPTION;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sicred.sistema.rest.exception.SessaoFechadaException;
import com.sicred.sistema.rest.exception.VotoDuplicadoException;
import com.sicred.sistema.rest.exception.VotoInvalidoException;
//import com.sicred.sistema.validator.CpfValidador;
import com.sicred.sistema.validator.ValidaCPF;
import com.sicred.sistema.entidades.Associado;
import com.sicred.sistema.entidades.Pauta;
import com.sicred.sistema.entidades.Voto;
import com.sicred.sistema.entidades.VotoPK;
import com.sicred.sistema.repository.VotoRepository;

@Service
public class VotoService {
	
	private static final Logger logger = LoggerFactory.getLogger(VotoService.class);

	@Autowired
    private VotoRepository votoRepository;
	
	@Autowired
	private PautaService pautaService;
	
	@Autowired
	private AssociadoService associadoService;
	
	public Optional<Voto> buscarPorId(Voto voto){
        return votoRepository.findById(obterVotoId(voto));
    }
	
	private VotoPK obterVotoId(Voto voto) {
        return VotoPK.builder()
                .idCooperado(voto.getIdCooperado())
                .idPauta(voto.getIdPauta())
                .build();
    }
	
    public Voto cadastrar(Voto voto) {
        validar(voto);
        validarVotoPorIdDePauta(voto.getIdPauta());
        logger.info("cadastrando novo voto: " + voto);
        if(voto.getIdCooperado() == null) {
        	Voto votoDoCpfJaExiste = votoRepository.findByCpf(voto.getCpf());
        	Associado associado = null;
        	if(votoDoCpfJaExiste == null && voto.getIdCooperado() == null) {
        		associado = new Associado();
        		associado.setCpf(voto.getCpf());
            	associado.setNome(voto.getNomeVotante());
            	associado = associadoService.cadastrar(associado);
            	voto.setIdCooperado(associado.getId());
            	return votoRepository.save(voto);
        	}
        	if(votoDoCpfJaExiste == null && voto.getIdCooperado() != null) {
        		return votoRepository.save(voto);
        	}
        }
        return null;
    }
    
    public void validarVotoPorIdDePauta(Long idPauta) {
        logger.info("validando pauta nº: "+ idPauta);
        Pauta pauta = pautaService.buscarPorId(idPauta);
        if (pauta.estahFechada()) {
            throw new SessaoFechadaException(SESSAO_FECHADA_EXCEPTION);
        }
    	   
    }
	
	public void validar(Voto voto) {
        logger.info("validando voto: " + voto);
        Optional<Voto> votoConsultado = votoRepository.findById(obterVotoId(voto));
        validar(voto.getVoto());
        if (votoConsultado.isPresent()) {
            throw new VotoDuplicadoException(VOTO_DUPLICADO_EXCEPTION);
        }
        ValidaCPF.isCPF(voto.getCpf());
    }

    private void validar(String voto) {
        if(!(voto.equals(SIM) || voto.equals(NAO))){
            throw new VotoInvalidoException(VOTO_INVALIDO_EXCEPTION);
        }
    }
}
