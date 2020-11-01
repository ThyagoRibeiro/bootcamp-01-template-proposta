package br.com.thyagoribeiro.proposta.domains.cartao;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Renegociacao {

    @Id
    private String id;

    private Long quantidade;

    private BigDecimal valor;

    private LocalDateTime dataCriacao;

    @Deprecated
    public Renegociacao() {
    }

    public Renegociacao(String id, Long quantidade, BigDecimal valor, LocalDateTime dataCriacao) {
        this.id = id;
        this.quantidade = quantidade;
        this.valor = valor;
        this.dataCriacao = dataCriacao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
