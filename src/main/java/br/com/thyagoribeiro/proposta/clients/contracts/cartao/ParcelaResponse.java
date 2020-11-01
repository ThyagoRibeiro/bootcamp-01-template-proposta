package br.com.thyagoribeiro.proposta.clients.contracts.cartao;

import br.com.thyagoribeiro.proposta.domains.cartao.Parcela;

import java.math.BigDecimal;

public class ParcelaResponse {

    private String id;

    private Long quantidade;

    private BigDecimal valor;

    public ParcelaResponse(String id, Long quantidade, BigDecimal valor) {
        this.id = id;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    public Parcela toModel() {
        return new Parcela(id, quantidade, valor);
    }
}
