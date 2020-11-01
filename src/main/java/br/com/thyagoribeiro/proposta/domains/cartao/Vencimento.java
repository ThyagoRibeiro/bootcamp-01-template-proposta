package br.com.thyagoribeiro.proposta.domains.cartao;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Vencimento {

    @Id
    private String id;

    private Long dia;

    private LocalDateTime dataCriacao;

    @Deprecated
    public Vencimento() {
    }

    public Vencimento(String id, Long dia, LocalDateTime dataCriacao) {
        this.id = id;
        this.dia = dia;
        this.dataCriacao = dataCriacao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getDia() {
        return dia;
    }

    public void setDia(Long dia) {
        this.dia = dia;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
