package br.com.thyagoribeiro.proposta.clients.contracts.cartao;

import br.com.thyagoribeiro.proposta.domains.cartao.Bloqueio;

public class BloqueiaCartaoRequest {

    private String sistemaResponsavel;

    @Deprecated
    public BloqueiaCartaoRequest() {
    }

    public BloqueiaCartaoRequest(Bloqueio bloqueio) {
        this.sistemaResponsavel = bloqueio.getSistemaResponsavel();
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }

    public void setSistemaResponsavel(String sistemaResponsavel) {
        this.sistemaResponsavel = sistemaResponsavel;
    }

}
