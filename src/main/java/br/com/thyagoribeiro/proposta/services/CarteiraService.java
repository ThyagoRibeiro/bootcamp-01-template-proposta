package br.com.thyagoribeiro.proposta.services;

import br.com.thyagoribeiro.proposta.clients.CartoesClient;
import br.com.thyagoribeiro.proposta.clients.contracts.CartaoCarteiraRequest;
import br.com.thyagoribeiro.proposta.domains.cartao.Cartao;
import br.com.thyagoribeiro.proposta.domains.cartao.Carteira;
import br.com.thyagoribeiro.proposta.handler.ErroPadronizado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.net.URI;
import java.util.Arrays;

// CDD Total - 4

@Component
@Transactional
public class CarteiraService {

    @Autowired
    private CartoesClient cartoesClient; // CDD 1 - Classe CartoesClient

    public ResponseEntity<?> criarCarteira(EntityManager entityManager,
                                           UriComponentsBuilder uriComponentsBuilder,
                                           Cartao cartao, // CDD 1 - Classe Cartao
                                           Carteira carteira) { // CDD 1 - Classe Carteira

        ResponseEntity<?> response = cartoesClient.carteiraCartao(cartao.getNumeroCartao(), new CartaoCarteiraRequest(carteira)); // CDD 1 - Classe CartaoCarteiraRequest
        if(!response.getStatusCode().is2xxSuccessful()) // CDD 1 - Branch if
            return ResponseEntity.unprocessableEntity().body(new ErroPadronizado(Arrays.asList("Sistema legado retornou erro"))); // CDD 1 - Classe ErroPadronizado

        cartao.getCarteiraList().add(carteira);
        entityManager.persist(cartao);

        URI consultaNovoRecurso = uriComponentsBuilder.build(cartao.getId(), carteira.getId());
        return ResponseEntity.created(consultaNovoRecurso).build();

    }

}
