package br.com.thyagoribeiro.proposta.clients.contracts;

import br.com.thyagoribeiro.proposta.domains.cartao.Aviso;

public class AvisoCartaoRequest {

    private String destino;

    private String validoAte;

    @Deprecated
    public AvisoCartaoRequest() {
    }

    public AvisoCartaoRequest(Aviso aviso) {
        this.destino = destino;
        this.validoAte = validoAte;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getValidoAte() {
        return validoAte;
    }

    public void setValidoAte(String validoAte) {
        this.validoAte = validoAte;
    }
}
