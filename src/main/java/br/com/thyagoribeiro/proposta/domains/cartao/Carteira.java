package br.com.thyagoribeiro.proposta.domains.cartao;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Carteira {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    private String idLegado;

    private String email;

    @JsonProperty("associadaEm")
    private LocalDateTime dataAssociacao;

    private String emissor;

    @ManyToOne(fetch = FetchType.LAZY)
    private Cartao cartao;

    @Deprecated
    public Carteira() {
    }

    public Carteira(String idLegado, String email, LocalDateTime dataAssociacao, String emissor) {
        this.idLegado = idLegado;
        this.email = email;
        this.dataAssociacao = dataAssociacao;
        this.emissor = emissor;
    }

    public Carteira(String email, LocalDateTime dataAssociacao, String emissor, Cartao cartao) {
        this.email = email;
        this.dataAssociacao = dataAssociacao;
        this.emissor = emissor;
        this.cartao = cartao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdLegado() {
        return idLegado;
    }

    public void setIdLegado(String idLegado) {
        this.idLegado = idLegado;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getDataAssociacao() {
        return dataAssociacao;
    }

    public void setDataAssociacao(LocalDateTime dataAssociacao) {
        this.dataAssociacao = dataAssociacao;
    }

    public String getEmissor() {
        return emissor;
    }

    public void setEmissor(String emissor) {
        this.emissor = emissor;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public void setCartao(Cartao cartao) {
        this.cartao = cartao;
    }
}
