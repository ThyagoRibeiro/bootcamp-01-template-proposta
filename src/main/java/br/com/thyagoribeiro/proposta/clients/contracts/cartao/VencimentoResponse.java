package br.com.thyagoribeiro.proposta.clients.contracts.cartao;

import br.com.thyagoribeiro.proposta.domains.cartao.Vencimento;

import java.time.LocalDateTime;

public class VencimentoResponse {

    private String id;

    private Long dia;

    private LocalDateTime dataDeCriacao;

    public VencimentoResponse(String id, Long dia, LocalDateTime dataDeCriacao) {
        this.id = id;
        this.dia = dia;
        this.dataDeCriacao = dataDeCriacao;
    }

    public Vencimento toModel() {
        return new Vencimento(id, dia, dataDeCriacao);
    }
}
