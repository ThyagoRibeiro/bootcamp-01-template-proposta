package rest;

import br.com.thyagoribeiro.proposta.domains.Proposta;
import br.com.thyagoribeiro.proposta.repositories.PropostaRepository;
import br.com.thyagoribeiro.proposta.rest.NovaPropostaController;
import br.com.thyagoribeiro.proposta.rest.contracts.NovaPropostaRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class NovaPropostaControllerTest {

    @Test
    @DisplayName("Testa criação de proposta da proposta controller")
    void novaPropostaTest() {

        EntityManager entityManager = Mockito.mock(EntityManager.class);
        PropostaRepository propostaRepository = Mockito.mock(PropostaRepository.class);

        NovaPropostaController novaPropostaController = new NovaPropostaController(entityManager, propostaRepository);
        NovaPropostaRequest novaPropostaRequest = new NovaPropostaRequest("40455606889", "teste@teste.com.br", "teste", "Rua 1", BigDecimal.valueOf(1000.00));

        UriComponentsBuilder uriComponentsBuilder =  UriComponentsBuilder.newInstance();

        ResponseEntity responseEntity = novaPropostaController.novaProposta(novaPropostaRequest, uriComponentsBuilder);

        Proposta proposta = novaPropostaRequest.toModel();

        //Mockito.verify(entityManager).persist(proposta);
        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

    }

}

