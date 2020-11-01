package br.com.thyagoribeiro.proposta.rest;

import br.com.thyagoribeiro.proposta.clients.AnaliseFinanceiraClient;
import br.com.thyagoribeiro.proposta.clients.contracts.AnaliseFinanceiraResponse;
import br.com.thyagoribeiro.proposta.domains.Proposta;
import br.com.thyagoribeiro.proposta.domains.StatusProposta;
import br.com.thyagoribeiro.proposta.repositories.PropostaRepository;
import br.com.thyagoribeiro.proposta.rest.contracts.NovaPropostaRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
public class NovaPropostaControllerTest {

    @InjectMocks
    private NovaPropostaController novaPropostaController;

    @Mock
    private EntityManager entityManager;

    @Mock
    private PropostaRepository propostaRepository; // CDD 1 - PropostaRepository

    @Mock
    private AnaliseFinanceiraClient analiseFinanceiraClient; // CDD 1 - AnaliseFinanceiraClient

    private ObjectMapper objectMapper;
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(this.novaPropostaController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("Testa criação de proposta, retornando 201")
    public void novaPropostaTest() throws Exception {

        NovaPropostaRequest novaPropostaRequest = new NovaPropostaRequest("39028957000181", "teste@teste.com.br", "Teste", "Teste", BigDecimal.valueOf(10.0));

        AnaliseFinanceiraResponse analiseFinanceiraResponse = new AnaliseFinanceiraResponse(novaPropostaRequest.getDocumento(), novaPropostaRequest.getNome(), "id", StatusProposta.ELEGIVEL.restricao);
        ResponseEntity responseEntity = new ResponseEntity<AnaliseFinanceiraResponse>(analiseFinanceiraResponse, HttpStatus.CREATED);

        doAnswer((InvocationOnMock invocation) -> {
            Proposta proposta = (Proposta) invocation.getArguments()[0];
            proposta.setId("1");
            return null;
        }).when(entityManager).persist(any());

        when(analiseFinanceiraClient.solicitacao(any())).thenReturn(responseEntity);

        ResultActions response = mockMvc.perform(
                post("/api/propostas")
                        .content(objectMapper.writeValueAsString(novaPropostaRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));

        response.andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Testa proposta já existente, retornando 422")
    public void propostaExistenteTest() throws Exception {

       NovaPropostaRequest novaPropostaRequest = new NovaPropostaRequest("39028957000181", "teste@teste.com.br", "Teste", "Teste", BigDecimal.valueOf(10.0));

       AnaliseFinanceiraResponse analiseFinanceiraResponse = new AnaliseFinanceiraResponse(novaPropostaRequest.getDocumento(), novaPropostaRequest.getNome(), "id", StatusProposta.ELEGIVEL.restricao);
       ResponseEntity responseEntity = new ResponseEntity<AnaliseFinanceiraResponse>(analiseFinanceiraResponse, HttpStatus.CREATED);

       when(propostaRepository.findByDocumento(any())).thenReturn(Optional.of(novaPropostaRequest.toModel()));
       when(analiseFinanceiraClient.solicitacao(any())).thenReturn(responseEntity);

       ResultActions response = mockMvc.perform(
               post("/api/propostas")
                       .content(objectMapper.writeValueAsString(novaPropostaRequest))
                       .contentType(MediaType.APPLICATION_JSON)
                       .accept(MediaType.APPLICATION_JSON));

       response.andExpect(status().isUnprocessableEntity());
    }

}
