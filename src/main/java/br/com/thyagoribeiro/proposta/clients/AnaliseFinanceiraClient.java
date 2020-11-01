package br.com.thyagoribeiro.proposta.clients;

import br.com.thyagoribeiro.proposta.clients.contracts.AnaliseFinanceiraRequest;
import br.com.thyagoribeiro.proposta.clients.contracts.AnaliseFinanceiraResponse;
import br.com.thyagoribeiro.proposta.handler.AnaliseFinanceiraClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "analiseFinanceira", url = "${analise.financeira.domain}", fallbackFactory = AnaliseFinanceiraClientFallback.class)
public interface AnaliseFinanceiraClient {

    @PostMapping(value = "/api/solicitacao", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AnaliseFinanceiraResponse> solicitacao(@RequestBody AnaliseFinanceiraRequest analiseFinanceiraRequest);

}
