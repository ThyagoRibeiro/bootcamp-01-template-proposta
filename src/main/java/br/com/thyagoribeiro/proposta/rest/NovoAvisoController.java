package br.com.thyagoribeiro.proposta.rest;

import br.com.thyagoribeiro.proposta.domains.cartao.Aviso;
import br.com.thyagoribeiro.proposta.domains.cartao.Cartao;
import br.com.thyagoribeiro.proposta.handler.ErroPadronizado;
import br.com.thyagoribeiro.proposta.rest.contracts.NovoAvisoRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Arrays;

@RestController
public class NovoAvisoController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/api/cartoes/{id_cartao}/avisos")
    @Transactional
    public ResponseEntity<?> novoAviso(@PathVariable("id_cartao") String cartaoId,
                                       @RequestHeader(value = "User-Agent") String userAgent,
                                       @RequestHeader(value = "Host") String host,
                                       @RequestBody @Valid NovoAvisoRequest novoAvisoRequest,
                                       UriComponentsBuilder uriComponentsBuilder) {

        Cartao cartao = entityManager.find(Cartao.class, cartaoId); // CDD 1 - Classe Cartao
        if(cartao == null) // CDD 1 - branch if
            return ResponseEntity.badRequest().body(new ErroPadronizado(Arrays.asList("Cartão não encontrado"))); // CDD 1 - Classe ErroPadronizado

        Aviso aviso = novoAvisoRequest.toModel(cartao, userAgent, host);
        cartao.getAvisoList().add(aviso);

        entityManager.persist(cartao);

        URI uri = uriComponentsBuilder.path("cartoes/{id_cartao}/avisos/{id_aviso}").build(cartao.getId(), aviso.getId());
        return ResponseEntity.created(uri).build();

    }

}
