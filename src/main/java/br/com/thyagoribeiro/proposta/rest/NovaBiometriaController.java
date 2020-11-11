package br.com.thyagoribeiro.proposta.rest;

import br.com.thyagoribeiro.proposta.domains.cartao.Biometria;
import br.com.thyagoribeiro.proposta.domains.cartao.Cartao;
import br.com.thyagoribeiro.proposta.handler.ErroPadronizado;
import br.com.thyagoribeiro.proposta.rest.contracts.NovaBiometriaRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
public class NovaBiometriaController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/api/cartoes/{id_cartao}/biometrias")
    @Transactional
    public ResponseEntity<?> novaBiometria(@PathVariable("id_cartao") String cartaoId,
                                            @RequestBody @Valid NovaBiometriaRequest novaBiometriaRequest,
                                            UriComponentsBuilder uriComponentsBuilder
                                           ) {

        Cartao cartao = entityManager.find(Cartao.class, cartaoId);

        if(cartao == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErroPadronizado("Cartão não encontrado"));

        Biometria biometria = novaBiometriaRequest.toModel(cartao);
        cartao.getBiometriaList().add(biometria);

        entityManager.persist(cartao);

        URI consultaNovoRecurso = uriComponentsBuilder.path("/api/cartoes/{id_cartao}/biometrias/{id_biometria}").build(cartaoId, biometria.getId());
        return ResponseEntity.created(consultaNovoRecurso).build();
    }

}
