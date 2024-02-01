package com.sicred.sistema.validator;

import static com.sicred.sistema.shared.Constantes.ABLE_TO_VOTE;
import static com.sicred.sistema.shared.Constantes.CPF_INVALIDO_EXCEPTION;
import static java.lang.String.format;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.sicred.sistema.config.CpfConfig;
import com.sicred.sistema.rest.dto.CpfDTO;
import com.sicred.sistema.rest.exception.CpfInvalidoException;

@Service
public class CpfValidador {
	
	private static final Logger logger = LoggerFactory.getLogger(CpfValidador.class);
	
	
	RestTemplate restTemplate = new RestTemplate();
	
	
	CpfConfig cpfConfig = new CpfConfig();

	public void validarCpf(String cpf) {
        logger.info("validando CPF: " +cpf);
        CpfDTO resposta = buscarCpf(cpf);

        ehApto(resposta);
    }
    
    private void ehApto(CpfDTO resposta) {
        if (!resposta.getStatus().equals(ABLE_TO_VOTE)) {
            throw new CpfInvalidoException(CPF_INVALIDO_EXCEPTION);
        }
    }
    
    private CpfDTO buscarCpf(String cpf) {
        String uri = obterUri(cpf);
        try {
            return restTemplate.getForObject(uri, CpfDTO.class);
        } catch (HttpClientErrorException e) {
            throw new CpfInvalidoException(CPF_INVALIDO_EXCEPTION);
        }
    }
    
    private String obterUri(String cpf) {
        return format(this.cpfConfig.getUrl(), cpf);
    }
}
