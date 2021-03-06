package br.com.thyagoribeiro.proposta.handler;

import br.com.thyagoribeiro.proposta.clients.AnaliseFinanceiraClient;
import br.com.thyagoribeiro.proposta.clients.contracts.analise_financeira.AnaliseFinanceiraRequest;
import br.com.thyagoribeiro.proposta.clients.contracts.analise_financeira.AnaliseFinanceiraResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import feign.hystrix.FallbackFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class AnaliseFinanceiraClientFallback implements FallbackFactory<AnaliseFinanceiraClient> {

    @Override
    public AnaliseFinanceiraClient create(Throwable cause) {
        return new AnaliseFinanceiraClient() {

            @Override
            public ResponseEntity<AnaliseFinanceiraResponse> solicitacao(AnaliseFinanceiraRequest analiseFinanceiraRequest) {

                if(cause instanceof FeignException) {

                    FeignException feignException = (FeignException) cause;

                    if(HttpStatus.valueOf(feignException.status()).is4xxClientError()
                        || HttpStatus.valueOf(feignException.status()).is5xxServerError()) {

                        ObjectMapper objectMapper = new ObjectMapper();
                        try {
                            return ResponseEntity.unprocessableEntity().body(objectMapper.readValue(feignException.contentUTF8(), AnaliseFinanceiraResponse.class));
                        } catch (JsonProcessingException e) {
                            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                        }

                    }
                }

                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }

        };
    }

}
