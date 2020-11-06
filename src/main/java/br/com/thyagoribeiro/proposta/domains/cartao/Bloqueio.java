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

    private String sistemaResponsavel;

    private boolean ativo;

    @ManyToOne(fetch = FetchType.LAZY)
    private Cartao cartao;

    private String userAgent;

    @Deprecated
    public Bloqueio() {
    }

    public Bloqueio(String id, LocalDateTime dataBloqueio, String sistemaResponsavel, boolean ativo) {
        this.id = id;
        this.dataBloqueio = dataBloqueio;
        this.sistemaResponsavel = sistemaResponsavel;
        this.ativo = ativo;
    }

    public Bloqueio(LocalDateTime dataBloqueio, String sistemaResponsavel, boolean ativo, Cartao cartao, String userAgent) {
        this.dataBloqueio = dataBloqueio;
        this.sistemaResponsavel = sistemaResponsavel;
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

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }

    public void setSistemaResponsavel(String sistemaResponsavel) {
        this.sistemaResponsavel = sistemaResponsavel;
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
