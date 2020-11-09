package br.com.thyagoribeiro.proposta.clients.contracts;

public class AvisoCartaoResponse {

    private String resultado;

    @Deprecated
    public AvisoCartaoResponse() {
    }

    public AvisoCartaoResponse(String resultado) {
        this.resultado = resultado;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

}
