package br.com.thyagoribeiro.proposta.clients.contracts.cartao;

import br.com.thyagoribeiro.proposta.domains.cartao.Aviso;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AvisoResponse {

    private LocalDate validoAte;

    private String destino;

    public AvisoResponse(LocalDate validoAte, String destino) {
        this.validoAte = validoAte;
        this.destino = destino;
    }

    public Aviso toModel() {
        return new Aviso(validoAte, destino);
    }
}
