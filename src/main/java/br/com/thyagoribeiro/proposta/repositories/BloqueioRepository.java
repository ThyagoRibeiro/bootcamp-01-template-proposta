package br.com.thyagoribeiro.proposta.repositories;

import br.com.thyagoribeiro.proposta.domains.cartao.Bloqueio;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BloqueioRepository extends CrudRepository<Bloqueio, String> {

    @Query("SELECT b FROM Bloqueio b JOIN FETCH b.cartao WHERE b.ativo = false")
    List<Bloqueio> findByAtivoFalse();

}
