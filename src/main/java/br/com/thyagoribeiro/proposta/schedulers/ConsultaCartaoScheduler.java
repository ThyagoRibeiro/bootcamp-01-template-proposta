package br.com.thyagoribeiro.proposta.schedulers;

import br.com.thyagoribeiro.proposta.clients.CartoesClient;
import br.com.thyagoribeiro.proposta.clients.contracts.cartao.BuscaCartaoResponse;
import br.com.thyagoribeiro.proposta.domains.proposta.Proposta;
import br.com.thyagoribeiro.proposta.domains.proposta.StatusProposta;
import br.com.thyagoribeiro.proposta.repositories.PropostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

// CDD Total = 5

@Component
public class ConsultaCartaoScheduler {

    @Autowired
    private PropostaRepository propostaRepository; // CDD 1 - PropostaRepository

    @Autowired
    private CartoesClient cartoesClient; // CDD 1 - CartoesClient

    @Scheduled(fixedDelayString = "${scheduler.busca.cartao}")
    @Transactional
    private void buscaCartao() {

        List<Proposta> propostaSemCartaoList = propostaRepository.findByStatusPropostaAndCartaoIdIsNull(StatusProposta.ELEGIVEL); // CDD 1 - Proposta

         if(propostaSemCartaoList.isEmpty()) // CDD 1 - branch if
            return;

        for(Proposta proposta : propostaSemCartaoList) {

            ResponseEntity<BuscaCartaoResponse> response = cartoesClient.buscaCartao(proposta.getId()); // CDD 1 - BuscaCartaoResponse

            if(response.getStatusCode().is2xxSuccessful()) {
                proposta.setCartao(response.getBody().toModel(proposta));
            }
        }

        propostaRepository.saveAll(propostaSemCartaoList);

    }

}
