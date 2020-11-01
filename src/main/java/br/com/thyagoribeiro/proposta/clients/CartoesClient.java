package br.com.thyagoribeiro.proposta.clients;

import br.com.thyagoribeiro.proposta.clients.contracts.cartao.BuscaCartaoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "cartoes", url = "${cartao.domain}")
public interface CartoesClient {

    @GetMapping(value = "/api/cartoes")
    public ResponseEntity<BuscaCartaoResponse> buscaCartao(@RequestParam("idProposta") String idProposta);


}
