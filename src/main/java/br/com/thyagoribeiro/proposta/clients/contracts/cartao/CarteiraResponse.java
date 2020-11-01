package br.com.thyagoribeiro.proposta.clients.contracts.cartao;

import br.com.thyagoribeiro.proposta.domains.cartao.Carteira;

import java.time.LocalDateTime;

public class CarteiraResponse {

    private String id;

    private String email;

    private LocalDateTime associadaEm;

    private String emissor;

    public CarteiraResponse(String id, String email, LocalDateTime associadaEm, String emissor) {
        this.id = id;
        this.email = email;
        this.associadaEm = associadaEm;
        this.emissor = emissor;
    }

    public Carteira toModel() {
        return new Carteira(id, email, associadaEm, emissor);
    }
}
