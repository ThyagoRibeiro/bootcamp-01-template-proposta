package br.com.thyagoribeiro.proposta.clients.contracts;

import br.com.thyagoribeiro.proposta.domains.cartao.Cartao;
import br.com.thyagoribeiro.proposta.domains.cartao.Carteira;

public class CartaoCarteiraRequest {

    private String email;
    private String carteira;

    @Deprecated
    public CartaoCarteiraRequest() {
    }

    public CartaoCarteiraRequest(Carteira carteira) {
        this.email = carteira.getEmail();
        this.carteira = carteira.getEmissor();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCarteira() {
        return carteira;
    }

    public void setCarteira(String carteira) {
        this.carteira = carteira;
    }
}
