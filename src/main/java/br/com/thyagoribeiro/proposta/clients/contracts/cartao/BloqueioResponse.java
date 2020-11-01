package br.com.thyagoribeiro.proposta.clients.contracts.cartao;

import br.com.thyagoribeiro.proposta.domains.cartao.Bloqueio;

import java.time.LocalDateTime;

public class BloqueioResponse {

    private String id;

    private LocalDateTime bloqueadoEm;

    private String sistemaResponsavel;

    private boolean ativo;

    public BloqueioResponse(String id, LocalDateTime bloqueadoEm, String sistemaResponsavel, boolean ativo) {
        this.id = id;
        this.bloqueadoEm = bloqueadoEm;
        this.sistemaResponsavel = sistemaResponsavel;
        this.ativo = ativo;
    }

    public Bloqueio toModel() {
        return new Bloqueio(id, bloqueadoEm, sistemaResponsavel, ativo);
    }
}
