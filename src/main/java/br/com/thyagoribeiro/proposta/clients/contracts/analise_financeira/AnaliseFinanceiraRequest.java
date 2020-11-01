package br.com.thyagoribeiro.proposta.clients.contracts.analise_financeira;

import br.com.thyagoribeiro.proposta.domains.proposta.Proposta;

public class AnaliseFinanceiraRequest {

    private String documento;
    private String nome;
    private String idProposta;

    @Deprecated
    public AnaliseFinanceiraRequest() {
    }

    public AnaliseFinanceiraRequest(Proposta proposta) {
        this.documento = proposta.getDocumento();
        this.nome = proposta.getNome();
        this.idProposta = proposta.getId();
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIdProposta() {
        return idProposta;
    }

    public void setIdProposta(String idProposta) {
        this.idProposta = idProposta;
    }
}
