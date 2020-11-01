package br.com.thyagoribeiro.proposta.rest;

import br.com.thyagoribeiro.proposta.domains.proposta.Proposta;
import br.com.thyagoribeiro.proposta.handler.ErroPadronizado;
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

    @GetMapping("/api/propostas/{id_proposta}")
    public ResponseEntity<?> buscaProposta(@PathVariable("id_proposta") @NotBlank String idProposta) {

        Proposta proposta = entityManager.find(Proposta.class, idProposta); // CDD 1 - Proposta

        if(proposta == null) // CDD 1 - branch if
            return ResponseEntity.badRequest().body(new ErroPadronizado(Arrays.asList("Proposta não encontrada")));

        return ResponseEntity.ok(proposta);

    }

}
