package br.com.thyagoribeiro.proposta.clients.contracts.analise_financeira;

public class AnaliseFinanceiraResponse {

    private String documento;
    private String nome;
    private String idProposta;
    private String resultadoSolicitacao;

    @Deprecated
    public AnaliseFinanceiraResponse() {
    }

    public AnaliseFinanceiraResponse(String documento, String nome, String idProposta, String resultadoSolicitacao) {
        this.documento = documento;
        this.nome = nome;
        this.idProposta = idProposta;
        this.resultadoSolicitacao = resultadoSolicitacao;
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

    public String getResultadoSolicitacao() {
        return resultadoSolicitacao;
    }

    public void setResultadoSolicitacao(String resultadoSolicitacao) {
        this.resultadoSolicitacao = resultadoSolicitacao;
    }
}
