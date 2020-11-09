package br.com.thyagoribeiro.proposta.clients.contracts.cartao;

public class BloqueiaCartaoResponse {

    private String resultado;

    public BloqueiaCartaoResponse(String resultado) {
        this.resultado = resultado;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
}
