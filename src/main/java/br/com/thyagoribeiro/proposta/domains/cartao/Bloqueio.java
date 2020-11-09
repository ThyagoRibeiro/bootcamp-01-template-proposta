package br.com.thyagoribeiro.proposta.domains.cartao;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Bloqueio {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    private LocalDateTime dataBloqueio;

    private String ip;

    private boolean ativo;

    @ManyToOne(fetch = FetchType.LAZY)
    private Cartao cartao;

    private String userAgent;

    @Deprecated
    public Bloqueio() {
    }

    public Bloqueio(String id, LocalDateTime dataBloqueio, String ip, boolean ativo) {
        this.id = id;
        this.dataBloqueio = dataBloqueio;
        this.ip = ip;
        this.ativo = ativo;
    }

    public Bloqueio(LocalDateTime dataBloqueio, Cartao cartao, String sistemaResponsavel, String userAgent, boolean ativo) {
        this.dataBloqueio = dataBloqueio;
        this.ip = sistemaResponsavel;
        this.ativo = ativo;
        this.cartao = cartao;
        this.userAgent = userAgent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getDataBloqueio() {
        return dataBloqueio;
    }

    public void setDataBloqueio(LocalDateTime dataBloqueio) {
        this.dataBloqueio = dataBloqueio;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public void setCartao(Cartao cartao) {
        this.cartao = cartao;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }
}
