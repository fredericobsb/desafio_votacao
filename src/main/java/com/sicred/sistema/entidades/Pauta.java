package com.sicred.sistema.entidades;

import static com.sicred.sistema.shared.Constantes.ABERTA;
import static com.sicred.sistema.shared.Constantes.FECHADA;
import static com.sicred.sistema.shared.Util.estaNuloOuVazio;
import java.time.LocalDateTime;
import java.util.Set;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sicred.sistema.rest.dto.SessaoDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "PAUTA")
public class Pauta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PAUTA")
    private Long id;

    @Column(name = "TITULO")
    private String titulo;

    @OneToMany
    @LazyCollection(value = LazyCollectionOption.FALSE)
    @JoinColumn(name = "ID_PAUTA", referencedColumnName = "ID_PAUTA")
    Set<Voto> votos;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "DTH_LIMITE")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime tempoLimite;

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public Set<Voto> getVotos() {
        return votos;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getTempoLimite() {
        return tempoLimite;
    }

    public LocalDateTime abrirVotacao(SessaoDTO sessaoDTO) {
        this.status = "ABERTA";
        return this.tempoLimite = obterTempoFinal(sessaoDTO);
    }

    private LocalDateTime obterTempoFinal(SessaoDTO sessaoDTO) {
        if (estaNuloOuVazio(sessaoDTO.getMinutos())) {
            return LocalDateTime.now().plusMinutes(1);
        } else {
            return LocalDateTime.now().plusMinutes(sessaoDTO.getMinutos());
        }
    }

    public boolean estahFechada() {
        if (naoEstahAberta() || venceuTempoLimite()) {
            this.status = FECHADA;
            return true;
        } else {
            return false;
        }
    }

    public boolean estahFechadaIhNaoFoiEnviada() {
        return estahFechada();
    }

    private boolean naoEstahAberta() {
        return !this.status.equals(ABERTA);
    }

    private boolean venceuTempoLimite() {
        LocalDateTime agora = LocalDateTime.now();
        return agora.isAfter(tempoLimite);
    }

    public void obterStatusFechadaCasoNulo(Pauta pauta) {
        if (estaNuloOuVazio(pauta.getStatus())) {
            this.status = FECHADA;
        }
    }

    @Override
    public String toString() {
        return "Pauta{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", votos=" + votos +
                ", status='" + status + '\'' +
                ", tempoLimite=" + tempoLimite +
                ", enviadoKafka=" +
                '}';
    }
}
