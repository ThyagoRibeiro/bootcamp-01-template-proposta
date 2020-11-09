package br.com.thyagoribeiro.proposta.rest.contracts;

import br.com.thyagoribeiro.proposta.domains.cartao.Aviso;
import br.com.thyagoribeiro.proposta.domains.cartao.Cartao;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class NovoAvisoRequest {

    @NotBlank
    private String destino;

    @NotNull
    @Future
    @JsonProperty("data_termino")
    private LocalDate dataTermino;

    @Deprecated
    public NovoAvisoRequest() {
    }

    public NovoAvisoRequest(@NotBlank String destino, @NotNull @Future LocalDate dataTermino) {
        this.destino = destino;
        this.dataTermino = dataTermino;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public LocalDate getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(LocalDate dataTermino) {
        this.dataTermino = dataTermino;
    }

    public Aviso toModel(Cartao cartao, String userAgent, String ip) {
        return new Aviso(dataTermino, destino, cartao, LocalDateTime.now(), userAgent, ip);
    }

}
