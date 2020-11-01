package br.com.thyagoribeiro.proposta.rest;

import br.com.thyagoribeiro.proposta.clients.AnaliseFinanceiraClient;
import br.com.thyagoribeiro.proposta.clients.contracts.analise_financeira.AnaliseFinanceiraRequest;
import br.com.thyagoribeiro.proposta.clients.contracts.analise_financeira.AnaliseFinanceiraResponse;
import br.com.thyagoribeiro.proposta.domains.proposta.Proposta;
import br.com.thyagoribeiro.proposta.domains.proposta.StatusProposta;
import br.com.thyagoribeiro.proposta.handler.ErroPadronizado;
import br.com.thyagoribeiro.proposta.repositories.PropostaRepository;
import br.com.thyagoribeiro.proposta.rest.contracts.NovaPropostaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Arrays;

// CDD Total: 6

@RestController
public class NovaPropostaController {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private PropostaRepository propostaRepository; // CDD 1 - PropostaRepository

    @Autowired
    private AnaliseFinanceiraClient analiseFinanceiraClient; // CDD 1 - AnaliseFinanceiraClient

    @PostMapping("/api/propostas")
    @Transactional
    public ResponseEntity<?> novaProposta(@RequestBody @Valid NovaPropostaRequest novaPropostaRequest, UriComponentsBuilder uriComponentsBuilder) { // CDD 1 - NovaPropostaRequest

        if(propostaRepository.findByDocumento(novaPropostaRequest.getDocumento()).isPresent()) // CDD 1 - branch if
            return ResponseEntity.unprocessableEntity().body(new ErroPadronizado(Arrays.asList("Esse documento já foi cadastrado em outra proposta")));

        Proposta proposta = novaPropostaRequest.toModel(); // CDD 1 - Proposta
        entityManager.persist(proposta);

        ResponseEntity<AnaliseFinanceiraResponse> response = analiseFinanceiraClient.solicitacao(new AnaliseFinanceiraRequest(proposta)); // CDD 1 - NovaPropostaRequest
        proposta.setStatusProposta(StatusProposta.getByRestricao(response.getBody().getResultadoSolicitacao()));
        entityManager.persist(proposta);

        URI consultaNovoRecurso = uriComponentsBuilder.path("/api/propostas/{id}").build(proposta.getId());
        return ResponseEntity.created(consultaNovoRecurso).build();
    }

}
