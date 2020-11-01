package br.com.thyagoribeiro.proposta.domains.cartao;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Vencimento {

    @Id
    private String id;

    private Long dia;

    private LocalDateTime dataCriacao;

    @Deprecated
    public Vencimento() {
    }

    public Vencimento(String id, Long dia, LocalDateTime dataCriacao) {
        this.id = id;
        this.dia = dia;
        this.dataCriacao = dataCriacao;
    }
}
