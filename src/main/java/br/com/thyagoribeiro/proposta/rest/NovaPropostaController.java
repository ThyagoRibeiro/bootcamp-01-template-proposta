package br.com.thyagoribeiro.proposta.rest;

import br.com.thyagoribeiro.proposta.domains.Proposta;
import br.com.thyagoribeiro.proposta.repositories.PropostaRepository;
import br.com.thyagoribeiro.proposta.rest.contracts.NovaPropostaRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

// CDD Total: 3

@RestController
public class NovaPropostaController {

    private EntityManager entityManager;
    private PropostaRepository propostaRepository;

    public NovaPropostaController(EntityManager entityManager, PropostaRepository propostaRepository) { // CDD 1 - PropostaRepository
        super();
        this.entityManager = entityManager;
        this.propostaRepository = propostaRepository;
    }

    @PostMapping("/api/propostas")
    @Transactional
    public ResponseEntity<?> novaProposta(@RequestBody @Valid NovaPropostaRequest novaPropostaRequest, UriComponentsBuilder uriComponentsBuilder) { // CDD 1 - NovaPropostaRequest

        Proposta proposta = novaPropostaRequest.toModel(); // CDD 1 - Proposta
        entityManager.persist(proposta);
        URI consultaNovoRecurso = uriComponentsBuilder.path("/api/propostas/{id}").build(proposta.getId());
        return ResponseEntity.created(consultaNovoRecurso).build();
    }


}
