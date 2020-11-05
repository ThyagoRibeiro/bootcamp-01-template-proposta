package br.com.thyagoribeiro.proposta.rest.contracts;

import br.com.thyagoribeiro.proposta.domains.proposta.Proposta;
import br.com.thyagoribeiro.proposta.domains.proposta.StatusProposta;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BuscaPropostaResponse {

    private String id;

    @JsonProperty("status")
    private StatusProposta statusProposta;

    public BuscaPropostaResponse() {
    }

    public BuscaPropostaResponse(Proposta proposta) {
        this.id = proposta.getId();
        this.statusProposta = proposta.getStatusProposta();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public StatusProposta getStatusProposta() {
        return statusProposta;
    }

    public void setStatusProposta(StatusProposta statusProposta) {
        this.statusProposta = statusProposta;
    }

}
