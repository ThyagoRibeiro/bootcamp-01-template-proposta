package br.com.thyagoribeiro.proposta.rest.contracts;

import br.com.thyagoribeiro.proposta.domains.cartao.Bloqueio;
import br.com.thyagoribeiro.proposta.domains.cartao.Cartao;

import java.time.LocalDateTime;

public class NovoBloqueioRequest {

    private String ip;

    @Deprecated
    public NovoBloqueioRequest() {
    }

    public NovoBloqueioRequest(String ip) {
        this.ip = ip;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Bloqueio toModel(Cartao cartao, String userAgent) {
        return new Bloqueio(LocalDateTime.now(), ip, false, cartao, userAgent);
    }

}
