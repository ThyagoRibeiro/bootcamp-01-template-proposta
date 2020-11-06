package br.com.thyagoribeiro.proposta.rest.contracts;

import br.com.thyagoribeiro.proposta.domains.cartao.Bloqueio;
import br.com.thyagoribeiro.proposta.domains.cartao.Cartao;

import java.time.LocalDateTime;

public class NovoBloqueiroRequest {

    private String ip;

    public NovoBloqueiroRequest(String ip) {
        this.ip = ip;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Bloqueio toModel(Cartao cartao) {
        return new Bloqueio(LocalDateTime.now(), ip, true, cartao);
    }

}
