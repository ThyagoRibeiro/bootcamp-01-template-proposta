package br.com.thyagoribeiro.proposta.rest;

import br.com.thyagoribeiro.proposta.domains.cartao.SolicitacaoSenha;
import br.com.thyagoribeiro.proposta.domains.cartao.Cartao;
import br.com.thyagoribeiro.proposta.handler.ErroPadronizado;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.Arrays;

// CDD Total - 4

@RestController
public class NovaSolicitacaoSenhaController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/api/cartoes/{id_cartao}/solicitacoes_senhas")
    @Transactional
    public ResponseEntity<?> novaSolicitacaoSenha(@PathVariable("id_cartao") String cartaoId,
                                                  @RequestHeader(value = "User-Agent") String userAgent,
                                                  @RequestHeader(value = "Host") String host,
                                                  UriComponentsBuilder uriComponentsBuilder) {

        Cartao cartao = entityManager.find(Cartao.class, cartaoId); // CDD 1 - Classe Cartao
        if (cartao == null) // CDD 1 - branch if
            return ResponseEntity.badRequest().body(new ErroPadronizado(Arrays.asList("Cartão não encontrado"))); // CDD 1 - Classe ErroPadronizado

        SolicitacaoSenha solicitacaoSenha = new SolicitacaoSenha(LocalDateTime.now(), cartao, host, userAgent); // CDD 1 - Classe SolicitacaoSenha
        cartao.getSolicitacaoSenhaList().add(solicitacaoSenha);
        entityManager.persist(cartao);

        URI uri = uriComponentsBuilder.path("cartoes/{id_cartao}/solicitacoes_senhas/{id_solicitacao_senha}").build(cartao.getId(), solicitacaoSenha.getId());
        return ResponseEntity.created(uri).build();

    }
}