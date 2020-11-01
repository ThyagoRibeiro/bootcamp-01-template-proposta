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
}
