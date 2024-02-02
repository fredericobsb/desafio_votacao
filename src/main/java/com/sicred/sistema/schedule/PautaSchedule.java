package com.sicred.sistema.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.sicred.sistema.entidades.Pauta;
import com.sicred.sistema.rest.dto.ResultadoDTO;
import com.sicred.sistema.service.PautaService;
import com.sicred.sistema.service.ResultadoService;
import static com.sicred.sistema.shared.Constantes.FECHADA;

@Component
@EnableScheduling
public class PautaSchedule {

    private static final Logger logger = LoggerFactory.getLogger(PautaSchedule.class);

    private final PautaService pautaService;
    private final ResultadoService resultadoService;

    @Autowired
    public PautaSchedule(PautaService pautaService,
                         ResultadoService resultadoService
                         ) {
        this.pautaService = pautaService;
        this.resultadoService = resultadoService;
    }

    @Scheduled(fixedDelay = 1000)
    public void fecharPautaCasoVerdadeiro() {
        pautaService.consultarPautasAbertas().stream()
                .filter(Pauta::estahFechadaIhNaoFoiEnviada).forEach(pauta -> {
            ResultadoDTO resultadoDTO = resultadoService.obterResultado(pauta.getId());
            atulizarResultado(resultadoDTO);
            logger.info("Enviando resultado :" + resultadoDTO);
            logger.info("salvando pauta fechada :" + pauta);
            pautaService.atualizarPauta(pauta);
        });
    }

    private void atulizarResultado(ResultadoDTO resultadoDTO) {
        resultadoDTO.setStatus(FECHADA);
    }
}