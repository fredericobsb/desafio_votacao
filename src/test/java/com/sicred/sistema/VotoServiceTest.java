package com.sicred.sistema;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import com.sicred.sistema.entidades.Associado;
import com.sicred.sistema.entidades.Pauta;
import com.sicred.sistema.entidades.Voto;
import com.sicred.sistema.entidades.VotoPK;
import com.sicred.sistema.repository.VotoRepository;
import com.sicred.sistema.service.AssociadoService;
import com.sicred.sistema.service.PautaService;
import com.sicred.sistema.service.VotoService;

import java.time.LocalDateTime;
import java.util.Optional;
import static com.sicred.sistema.builders.VotoBuilder.umVoto;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class VotoServiceTest {

	@InjectMocks
    private VotoService votoService;
	
	private PautaService pautaService;

    private VotoRepository votoRepository;
    
    private AssociadoService associadoService;

    @BeforeEach
    public void setUp() {
    	associadoService = Mockito.mock(AssociadoService.class);
    	pautaService = Mockito.mock(PautaService.class);
    	votoRepository = Mockito.mock(VotoRepository.class);
    	MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("deve cadastrar voto de usuário NÃO Registrado ainda no sistema")
    public void deveCadastrarVotoComSucessoDeUsuarioNaoRegistrado() {
    	Voto voto = new Voto();
    	voto.setIdPauta(1L);
    	voto.setCpf("10338927425");
    	voto.setVoto("Sim");
        Mockito.when(votoRepository.save(any(Voto.class))).thenReturn(voto);
        Optional<Voto>optionalVoto = Optional.empty();
        Mockito.when(votoRepository.findById(any())).thenReturn(optionalVoto);
        Pauta pauta = new Pauta();
        pauta.setId(1L);
        pauta.setStatus("ABERTA");
        pauta.setTempoLimite(LocalDateTime.of(2025, 12, 30, 12, 59));
        Mockito.when(pautaService.buscarPorId(any(Long.class))).thenReturn(pauta);
        Mockito.when(votoRepository.findByCpf(any(String.class))).thenReturn(null);
        Associado associado = new Associado();
        associado.setId(1L);
        Mockito.when(associadoService.cadastrar(any(Associado.class))).thenReturn(associado);
        Voto esperado = votoService.cadastrar(voto);

        Assertions.assertEquals(esperado.getCpf(), voto.getCpf());
        Assertions.assertEquals(esperado.getVoto(), voto.getVoto());
        Assertions.assertEquals(esperado.getIdPauta(), voto.getIdPauta());
    }
    
    @Test
    @DisplayName("deve cadastrar voto de cooperado JÁ Registrado no sistema")
    public void deveCadastrarVotoComSucessoDeUsuarioJaRegistrado() {
    	Voto voto = new Voto();
    	voto.setIdPauta(1L);
    	voto.setCpf("10338927425");
    	voto.setVoto("Sim");
    	
        Mockito.when(votoRepository.save(any(Voto.class))).thenReturn(voto);
        Optional<Voto>optionalVoto = Optional.empty();
        Mockito.when(votoRepository.findById(any())).thenReturn(optionalVoto);
        Pauta pauta = new Pauta();
        pauta.setId(1L);
        pauta.setStatus("ABERTA");
        pauta.setTempoLimite(LocalDateTime.of(2025, 12, 30, 12, 59));
        Mockito.when(pautaService.buscarPorId(any(Long.class))).thenReturn(pauta);
        Mockito.when(votoRepository.findByCpf(any(String.class))).thenReturn(null);
        Associado associado = new Associado();
        associado.setId(1L);
        Mockito.when(associadoService.cadastrar(any(Associado.class))).thenReturn(associado);
        Voto esperado = votoService.cadastrar(voto);

        Assertions.assertEquals(esperado.getCpf(), voto.getCpf());
        Assertions.assertEquals(esperado.getVoto(), voto.getVoto());
        Assertions.assertEquals(esperado.getIdPauta(), voto.getIdPauta());
    }

    @Test
    @DisplayName("deve buscar voto por id")
    public void deveBuscarVotoPorId() {
        Mockito.when(votoRepository.findById(any(VotoPK.class))).thenReturn(Optional.of(umVoto()));
        Optional<Voto> esperado = votoService.buscarPorId(umVoto());
        Assertions.assertTrue(esperado.isPresent());
    }
}

