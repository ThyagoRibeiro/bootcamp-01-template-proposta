package br.com.thyagoribeiro.proposta.clients;

import br.com.thyagoribeiro.proposta.clients.contracts.AvisoCartaoRequest;
import br.com.thyagoribeiro.proposta.clients.contracts.AvisoCartaoResponse;
import br.com.thyagoribeiro.proposta.clients.contracts.cartao.BloqueiaCartaoResponse;
import br.com.thyagoribeiro.proposta.clients.contracts.cartao.BloqueiaCartaoRequest;
import br.com.thyagoribeiro.proposta.clients.contracts.cartao.BuscaCartaoResponse;
import br.com.thyagoribeiro.proposta.handler.CartaoClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "cartoes", url = "${cartao.domain}", fallbackFactory = CartaoClientFallback.class)
public interface CartoesClient {

    @GetMapping(value = "/api/cartoes")
    public ResponseEntity<BuscaCartaoResponse> buscaCartao(@RequestParam("idProposta") String idProposta);

    @PostMapping(value = "/api/cartoes/{id}/bloqueios")
    public ResponseEntity<BloqueiaCartaoResponse> bloqueiaCartao(@PathVariable("id") String numeroCartao, BloqueiaCartaoRequest bloqueiaCartaoRequest);

    @PostMapping(value = "/api/cartoes/{id}/avisos")
    public ResponseEntity<AvisoCartaoResponse> avisoCartao(@PathVariable("id") String numeroCartao, AvisoCartaoRequest avisoCartaoRequest);

}
