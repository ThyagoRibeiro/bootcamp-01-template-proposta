package br.com.thyagoribeiro.proposta.rest;

import br.com.thyagoribeiro.proposta.domains.cartao.Cartao;
import br.com.thyagoribeiro.proposta.domains.cartao.TipoCarteira;
import br.com.thyagoribeiro.proposta.handler.ErroPadronizado;
import br.com.thyagoribeiro.proposta.rest.contracts.NovaCarteiraPaypalRequest;
import br.com.thyagoribeiro.proposta.services.CarteiraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.Valid;

// CDD Total - 5

@RestController
public class NovaCarteiraPaypalController {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private CarteiraService carteiraService; // CDD 1 - Classe CartoesClient

    @PostMapping("/api/cartoes/{id_cartao}/carteiras_paypal")
    public ResponseEntity<?> novaCarteiraPaypal(@PathVariable("id_cartao") String cartaoId,
                                          @RequestBody @Valid NovaCarteiraPaypalRequest novaCarteiraPaypalRequest, // CDD 1 - Classe NovaCarteiraPaypalRequest
                                          UriComponentsBuilder uriComponentsBuilder) {

        Cartao cartao = entityManager.find(Cartao.class, cartaoId); // CDD 1 - Classe Cartao

        if(cartao == null) // CDD 1 - Branch if
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErroPadronizado("Cartão não encontrado"));

        Query query = entityManager.createQuery("SELECT c FROM Carteira c WHERE cartao_id = :cartaoId AND emissor = 'PAYPAL'");
        query.setParameter("cartaoId", cartaoId);

        if(!query.getResultList().isEmpty()) // CDD 1 - Branch if
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new ErroPadronizado("Carteira já cadastrada para esse cartão"));

        uriComponentsBuilder.path("/api/cartoes/{id_cartao}/carteiras_paypal/{id}");
        return carteiraService.criarCarteira(entityManager, uriComponentsBuilder, cartao, novaCarteiraPaypalRequest.toModel(cartao, TipoCarteira.PAYPAL));

    }

}
