package br.com.thyagoribeiro.proposta.rest;

import br.com.thyagoribeiro.proposta.clients.CartoesClient;
import br.com.thyagoribeiro.proposta.clients.contracts.cartao.BloqueiaCartaoRequest;
import br.com.thyagoribeiro.proposta.domains.cartao.Bloqueio;
import br.com.thyagoribeiro.proposta.domains.cartao.Cartao;
import br.com.thyagoribeiro.proposta.handler.ErroPadronizado;
import br.com.thyagoribeiro.proposta.rest.contracts.NovoBloqueiroRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.net.URI;
import java.security.Principal;
import java.util.Arrays;

@RestController
public class NovoBloqueioController {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private CartoesClient cartoesClient;

    @PostMapping("/api/cartoes/{id_cartao}/bloqueios")
    @Transactional
    public ResponseEntity<?> novoBloqueio(@PathVariable("id_cartao") String cartaoId,
                                          NovoBloqueiroRequest novoBloqueiroRequest,
                                          Principal principal,
                                          UriComponentsBuilder uriComponentsBuilder) {

        Cartao cartao = entityManager.find(Cartao.class, cartaoId);
        if(cartao == null)
            return ResponseEntity.badRequest().body(new ErroPadronizado(Arrays.asList("Cartão não encontrado")));

        Bloqueio bloqueio = novoBloqueiroRequest.toModel(cartao);
        cartao.getBloqueioList().add(bloqueio);

        ResponseEntity<?> response = cartoesClient.bloqueiaCartao(cartao.getNumeroCartao(), new BloqueiaCartaoRequest(bloqueio));
        if(response.getStatusCode().is2xxSuccessful())
            bloqueio.setAtivo(true);

        entityManager.persist(cartao);

        URI uri = uriComponentsBuilder.path("/api/propostas/{id}").build(bloqueio.getId());
        return ResponseEntity.created(uri).build();
    }

}
