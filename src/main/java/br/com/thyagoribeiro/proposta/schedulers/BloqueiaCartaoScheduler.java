package br.com.thyagoribeiro.proposta.schedulers;

import br.com.thyagoribeiro.proposta.clients.CartoesClient;
import br.com.thyagoribeiro.proposta.clients.contracts.cartao.BloqueiaCartaoRequest;
import br.com.thyagoribeiro.proposta.clients.contracts.cartao.BloqueiaCartaoResponse;
import br.com.thyagoribeiro.proposta.domains.cartao.Bloqueio;
import br.com.thyagoribeiro.proposta.domains.cartao.Cartao;
import br.com.thyagoribeiro.proposta.repositories.BloqueioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;


@Component
public class BloqueiaCartaoScheduler {

    @Autowired
    private BloqueioRepository bloqueioRepository; // CDD 1 - Classe BloqueioRepository

    @Autowired
    private CartoesClient cartoesClient; // CDD 1 - Classe CartoesClient

    @PersistenceContext
    private EntityManager entityManager;

    @Scheduled(fixedDelayString = "${scheduler.busca.cartao}")
    @Transactional
    public void bloqueia() {

        List<Bloqueio> bloqueioInativoList = bloqueioRepository.findByAtivoFalse();

        if(bloqueioInativoList.isEmpty()) // CDD 1 - branch if
            return;

        for(Bloqueio bloqueioInativo : bloqueioInativoList) {

            ResponseEntity<BloqueiaCartaoResponse> response = cartoesClient.bloqueiaCartao(bloqueioInativo.getCartao().getNumeroCartao(), new BloqueiaCartaoRequest("Cartão Branco Proposta")); // CDD 1 - Classe BloqueiaCartaoRequest
            bloqueioInativo.setAtivo(response.getStatusCode().is2xxSuccessful());

        }

        bloqueioRepository.saveAll(bloqueioInativoList);

    }

}
