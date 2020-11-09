package br.com.thyagoribeiro.proposta.rest;

import br.com.thyagoribeiro.proposta.clients.CartoesClient;
import br.com.thyagoribeiro.proposta.clients.contracts.cartao.BloqueiaCartaoRequest;
import br.com.thyagoribeiro.proposta.domains.cartao.Bloqueio;
import br.com.thyagoribeiro.proposta.domains.cartao.Cartao;
import br.com.thyagoribeiro.proposta.handler.ErroPadronizado;
import br.com.thyagoribeiro.proposta.rest.contracts.NovoBloqueioRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.net.URI;
import java.util.Arrays;

// CDD Total - 5

@RestController
public class NovoBloqueioController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/api/cartoes/{id_cartao}/bloqueios")
    @Transactional
    public ResponseEntity<?> novoBloqueio(@PathVariable("id_cartao") String cartaoId,
                                          @RequestBody NovoBloqueioRequest novoBloqueiroRequest, // CDD 1 - Classe CartoesClient
                                          @RequestHeader(value = "User-Agent") String userAgent,
                                          UriComponentsBuilder uriComponentsBuilder) {

        Cartao cartao = entityManager.find(Cartao.class, cartaoId); // CDD 1 - Classe Cartao
        if(cartao == null) // CDD 1 - branch if
            return ResponseEntity.badRequest().body(new ErroPadronizado(Arrays.asList("Cartão não encontrado"))); // CDD 1 - Classe ErroPadronizado

        Bloqueio bloqueio = novoBloqueiroRequest.toModel(cartao, userAgent); // CDD 1 - Classe Bloqueio
        cartao.getBloqueioList().add(bloqueio);

        entityManager.persist(cartao);

        URI uri = uriComponentsBuilder.path("/api/propostas/{id}").build(bloqueio.getId());
        return ResponseEntity.created(uri).build();
    }

}
