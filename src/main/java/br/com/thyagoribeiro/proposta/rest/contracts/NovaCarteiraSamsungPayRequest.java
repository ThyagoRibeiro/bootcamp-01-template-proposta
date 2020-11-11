package br.com.thyagoribeiro.proposta.rest.contracts;

import br.com.thyagoribeiro.proposta.domains.cartao.Cartao;
import br.com.thyagoribeiro.proposta.domains.cartao.Carteira;
import br.com.thyagoribeiro.proposta.domains.cartao.TipoCarteira;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class NovaCarteiraSamsungPayRequest {

    @Email
    @NotBlank
    private String email;

    @Deprecated
    public NovaCarteiraSamsungPayRequest() {
    }

    public NovaCarteiraSamsungPayRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Carteira toModel(Cartao cartao, TipoCarteira tipoCarteira) {
        return new Carteira(this.email, LocalDateTime.now(), tipoCarteira.name(), cartao);
    }
}
