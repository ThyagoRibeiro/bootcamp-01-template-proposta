package br.com.thyagoribeiro.proposta.domains;

import br.com.thyagoribeiro.proposta.domains.cartao.Cartao;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Biometria {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @NotNull
    private String codigo;

    private LocalDateTime instanteCriacao;

    @ManyToOne(fetch = FetchType.LAZY)
    private Cartao cartao;

    @Deprecated
    public Biometria() {
    }

    public Biometria(@NotNull String codigo, LocalDateTime instanteCriacao, Cartao cartao) {
        this.id = id;
        this.codigo = codigo;
        this.instanteCriacao = instanteCriacao;
        this.cartao = cartao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public LocalDateTime getInstanteCriacao() {
        return instanteCriacao;
    }

    public void setInstanteCriacao(LocalDateTime instanteCriacao) {
        this.instanteCriacao = instanteCriacao;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public void setCartao(Cartao cartao) {
        this.cartao = cartao;
    }



}
