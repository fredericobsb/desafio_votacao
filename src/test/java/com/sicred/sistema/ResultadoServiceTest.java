package com.sicred.sistema;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import com.sicred.sistema.entidades.Pauta;
import com.sicred.sistema.entidades.Voto;
import com.sicred.sistema.service.PautaService;
import com.sicred.sistema.service.ResultadoService;
import com.sicred.sistema.rest.dto.ResultadoDTO;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.HashSet;
import java.util.Set;
import static org.mockito.ArgumentMatchers.any;
import org.junit.runner.RunWith;
import static com.sicred.sistema.shared.Constantes.*;

@RunWith(MockitoJUnitRunner.class)
public class ResultadoServiceTest {

	@InjectMocks
    private ResultadoService resultadoService;
	
    private PautaService pautaService;

    @BeforeEach
    public void setUp() {
        pautaService = Mockito.mock(PautaService.class);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("resultado da pauta deve ser Sim")
    public void resultadoDaPautaDeveSerSim() {
    	Pauta pauta = new Pauta();
    	pauta.setId(1l);
    	Voto voto = new Voto();
    	voto.setCpf("22420579011");
    	voto.setIdCooperado(1L);
    	voto.setIdPauta(1L);
    	voto.setNomeVotante("Leandro Souza");
    	voto.setVoto("Sim");
    	Set<Voto>setDeVotos = new HashSet<>();
    	setDeVotos.add(voto);
    	pauta.setVotos(setDeVotos);
        Mockito.when(pautaService.buscarPorId(any(Long.class))).thenReturn(pauta);

        ResultadoDTO resultadoDTO = resultadoService.obterResultado(1L);
        Assertions.assertEquals(resultadoDTO.getResultado(), SIM);
    }

    
    @Test
    @DisplayName("resultado da pauta deve ser Não")
    public void resultadoDaPautaDeveSerNao() {
    	Pauta pauta = new Pauta();
    	pauta.setId(1l);
    	Voto voto = new Voto();
    	voto.setCpf("83270620004");
    	voto.setIdCooperado(1L);
    	voto.setIdPauta(1L);
    	voto.setNomeVotante("Francisco Duarte");
    	voto.setVoto("Não");
    	Set<Voto>setDeVotos = new HashSet<>();
    	setDeVotos.add(voto);
    	pauta.setVotos(setDeVotos);
        Mockito.when(pautaService.buscarPorId(any(Long.class))).thenReturn(pauta);

        ResultadoDTO resultadoDTO = resultadoService.obterResultado(1L);
        Assertions.assertEquals(resultadoDTO.getResultado(), NAO);
    }
    

    @Test
    @DisplayName("resultado da pauta deve ser Empate")
    public void resultadoDaPautaDeveSerEmpate() {
    	Pauta pauta = new Pauta();
    	pauta.setId(1l);
    	
    	Voto votoSim = new Voto();
    	votoSim.setCpf("83270620004");
    	votoSim.setIdCooperado(1L);
    	votoSim.setIdPauta(1L);
    	votoSim.setNomeVotante("Francisco Duarte");
    	votoSim.setVoto("Sim");
    	
    	Voto votoNao = new Voto();
    	votoNao.setCpf("83270620004");
    	votoNao.setIdCooperado(1L);
    	votoNao.setIdPauta(1L);
    	votoNao.setNomeVotante("Francisco Duarte");
    	votoNao.setVoto("Não");
    	
    	Set<Voto>setDeVotos = new HashSet<>();
    	setDeVotos.add(votoNao);
    	setDeVotos.add(votoSim);
    	
    	pauta.setVotos(setDeVotos);
        Mockito.when(pautaService.buscarPorId(any(Long.class))).thenReturn(pauta);

        ResultadoDTO resultadoDTO = resultadoService.obterResultado(1L);
        Assertions.assertEquals(resultadoDTO.getResultado(), EMPATE);
    }
}
