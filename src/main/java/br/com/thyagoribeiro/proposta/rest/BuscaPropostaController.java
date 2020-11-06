package br.com.thyagoribeiro.proposta.rest;

import br.com.thyagoribeiro.proposta.domains.proposta.Proposta;
import br.com.thyagoribeiro.proposta.handler.ErroPadronizado;
import br.com.thyagoribeiro.proposta.rest.contracts.BuscaPropostaResponse;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.constraints.NotBlank;
import java.util.Arrays;

@RestController
public class BuscaPropostaController {

    @PersistenceContext
    private EntityManager entityManager;

    private Timer consultarPropostaTimer;

    public BuscaPropostaController(MeterRegistry meterRegistry) {
        consultarPropostaTimer = meterRegistry.timer("proposta_consultada");
    }

    @GetMapping("/api/propostas/{id_proposta}")
    public ResponseEntity<?> buscaProposta(@PathVariable("id_proposta") @NotBlank String idProposta) {

        Timer.Sample timerSample = Timer.start();
        Proposta proposta = entityManager.find(Proposta.class, idProposta); // CDD 1 - Proposta
        timerSample.stop(consultarPropostaTimer);

        if(proposta == null) // CDD 1 - branch if
            return ResponseEntity.badRequest().body(new ErroPadronizado(Arrays.asList("Proposta não encontrada")));

        return ResponseEntity.ok(new BuscaPropostaResponse(proposta));

    }

}
