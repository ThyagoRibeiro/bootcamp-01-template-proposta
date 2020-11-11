package br.com.thyagoribeiro.proposta.handler;

import br.com.thyagoribeiro.proposta.clients.CartoesClient;
import br.com.thyagoribeiro.proposta.clients.contracts.AvisoCartaoRequest;
import br.com.thyagoribeiro.proposta.clients.contracts.AvisoCartaoResponse;
import br.com.thyagoribeiro.proposta.clients.contracts.CartaoCarteiraRequest;
import br.com.thyagoribeiro.proposta.clients.contracts.CarteiraCartaoResponse;
import br.com.thyagoribeiro.proposta.clients.contracts.cartao.BloqueiaCartaoResponse;
import br.com.thyagoribeiro.proposta.clients.contracts.cartao.BloqueiaCartaoRequest;
import br.com.thyagoribeiro.proposta.clients.contracts.cartao.BuscaCartaoResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import feign.hystrix.FallbackFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CartaoClientFallback implements FallbackFactory<CartoesClient> {

    @Override
    public CartoesClient create(Throwable cause) {
        return new CartoesClient() {

            @Override
            public ResponseEntity<BuscaCartaoResponse> buscaCartao(String idProposta) {
                return null;
            }

            @Override
            public ResponseEntity<BloqueiaCartaoResponse> bloqueiaCartao(String numeroCartao, BloqueiaCartaoRequest bloqueiaCartaoRequest) {
                return retornaHttpStatus();
            }

            @Override
            public ResponseEntity<AvisoCartaoResponse> avisoCartao(String numeroCartao, AvisoCartaoRequest avisoCartaoRequest) {
                return retornaHttpStatus();
            }

            @Override
            public ResponseEntity<CarteiraCartaoResponse> carteiraCartao(String numeroCartao, CartaoCarteiraRequest cartaoCarteiraRequest) {
                return retornaHttpStatus();
            }

            private ResponseEntity retornaHttpStatus() {
                if(cause instanceof FeignException) {

                    FeignException feignException = (FeignException) cause;

                    if(HttpStatus.valueOf(feignException.status()).is4xxClientError()
                            || HttpStatus.valueOf(feignException.status()).is5xxServerError()) {

                        ObjectMapper objectMapper = new ObjectMapper();
                        try {
                            return ResponseEntity.unprocessableEntity().body(objectMapper.readValue(feignException.contentUTF8(), BloqueiaCartaoResponse.class));
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
