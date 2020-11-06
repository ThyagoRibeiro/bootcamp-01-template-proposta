package br.com.thyagoribeiro.proposta.rest.contracts;

import br.com.thyagoribeiro.proposta.domains.Biometria;
import br.com.thyagoribeiro.proposta.domains.cartao.Cartao;
import br.com.thyagoribeiro.proposta.validators.Base64;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class NovaBiometriaRequest {

    @NotNull
    @Base64
    private String codigo;

    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public NovaBiometriaRequest() {
    }

    public NovaBiometriaRequest(@NotNull String codigo, Cartao cartao) {
        this.codigo = codigo;
        this.cartao = cartao;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public void setCartao(Cartao cartao) {
        this.cartao = cartao;
    }

    public Biometria toModel(Cartao cartao) {
        return new Biometria(codigo, LocalDateTime.now(), cartao);
    }

}
