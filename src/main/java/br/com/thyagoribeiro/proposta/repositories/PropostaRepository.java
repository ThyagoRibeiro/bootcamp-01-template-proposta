package br.com.thyagoribeiro.proposta.repositories;

import br.com.thyagoribeiro.proposta.domains.Proposta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PropostaRepository extends CrudRepository<Proposta, String> {

    Optional<Proposta> findByDocumento(String documento);

}
