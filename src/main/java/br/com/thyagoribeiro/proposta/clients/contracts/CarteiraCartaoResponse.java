package br.com.thyagoribeiro.proposta.clients.contracts;

public class CarteiraCartaoResponse {

    private String resultado;
    private String id;

    @Deprecated
    public CarteiraCartaoResponse() {
    }

    public CarteiraCartaoResponse(String resultado, String id) {
        this.resultado = resultado;
        this.id = id;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
