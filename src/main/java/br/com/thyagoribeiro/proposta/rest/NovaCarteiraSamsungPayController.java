package br.com.thyagoribeiro.proposta.rest;

import br.com.thyagoribeiro.proposta.domains.cartao.Cartao;
import br.com.thyagoribeiro.proposta.domains.cartao.TipoCarteira;
import br.com.thyagoribeiro.proposta.handler.ErroPadronizado;
import br.com.thyagoribeiro.proposta.rest.contracts.NovaCarteiraSamsungPayRequest;
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
import javax.validation.Valid;

// CDD Total - 5

@RestController
public class NovaCarteiraSamsungPayController {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private CarteiraService carteiraService; // CDD 1 - Classe CartoesClient

    @PostMapping("/api/cartoes/{id_cartao}/carteiras_samsung_pay")
    public ResponseEntity<?> novaCarteira(@PathVariable("id_cartao") String cartaoId,
                                          @RequestBody @Valid NovaCarteiraSamsungPayRequest novaCarteiraSamsungPayRequest, // CDD 1 - Classe NovaCarteiraPaypalRequest
                                          UriComponentsBuilder uriComponentsBuilder) {

        Cartao cartao = entityManager.find(Cartao.class, cartaoId); // CDD 1 - Classe Cartao

        if(cartao == null) // CDD 1 - Branch if
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErroPadronizado("Cartão não encontrado"));

        uriComponentsBuilder.path("/api/cartoes/{id_cartao}/carteiras_samsung_pay/{id}");
        return carteiraService.criarCarteira(entityManager, uriComponentsBuilder, cartao, novaCarteiraSamsungPayRequest.toModel(cartao, TipoCarteira.SAMSUNG_PAY));

    }

}
