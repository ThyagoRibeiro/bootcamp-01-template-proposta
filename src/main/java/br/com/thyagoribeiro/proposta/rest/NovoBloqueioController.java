package br.com.thyagoribeiro.proposta.rest;

import br.com.thyagoribeiro.proposta.domains.cartao.Bloqueio;
import br.com.thyagoribeiro.proposta.domains.cartao.Cartao;
import br.com.thyagoribeiro.proposta.handler.ErroPadronizado;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.Arrays;

// CDD Total - 4

@RestController
public class NovoBloqueioController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/api/cartoes/{id_cartao}/bloqueios")
    @Transactional
    public ResponseEntity<?> novoBloqueio(@PathVariable("id_cartao") String cartaoId,
                                          @RequestHeader(value = "User-Agent") String userAgent,
                                          @RequestHeader(value = "Host") String host,
                                          UriComponentsBuilder uriComponentsBuilder) {

        Cartao cartao = entityManager.find(Cartao.class, cartaoId); // CDD 1 - Classe Cartao
        if(cartao == null) // CDD 1 - branch if
            return ResponseEntity.badRequest().body(new ErroPadronizado(Arrays.asList("Cartão não encontrado"))); // CDD 1 - Classe ErroPadronizado

        Bloqueio bloqueio = new Bloqueio(LocalDateTime.now(), cartao, host, userAgent, false); // CDD 1 - Classe Bloqueio
        cartao.getBloqueioList().add(bloqueio);

        entityManager.persist(cartao);

        URI uri = uriComponentsBuilder.path("cartoes/{id_cartao}/bloqueios/{id_bloqueio}").build(cartao.getId(), bloqueio.getId());
        return ResponseEntity.created(uri).build();
    }

}
