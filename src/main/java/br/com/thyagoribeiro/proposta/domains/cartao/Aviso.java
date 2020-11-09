package br.com.thyagoribeiro.proposta.domains.cartao;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Aviso {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    String id;

    private LocalDate dataValidade;

    private String destino;

    @ManyToOne(fetch = FetchType.LAZY)
    private Cartao cartao;

    private LocalDateTime instanteAviso;

    private String ip;

    private String userAgent;

    @Deprecated
    public Aviso() {
    }

    public Aviso(LocalDate dataValidade, String destino) {
        this.dataValidade = dataValidade;
        this.destino = destino;
    }

    public Aviso(LocalDate dataValidade, String destino, Cartao cartao, LocalDateTime instanteAviso, String ip, String userAgent) {
        this.dataValidade = dataValidade;
        this.destino = destino;
        this.cartao = cartao;
        this.instanteAviso = instanteAviso;
        this.ip = ip;
        this.userAgent = userAgent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public void setCartao(Cartao cartao) {
        this.cartao = cartao;
    }

}
