package br.com.thyagoribeiro.proposta.repositories;

import br.com.thyagoribeiro.proposta.domains.proposta.Proposta;
import br.com.thyagoribeiro.proposta.domains.proposta.StatusProposta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PropostaRepository extends CrudRepository<Proposta, String> {

    Optional<Proposta> findByDocumento(String documento);

    List<Proposta> findByStatusPropostaAndCartaoIdIsNull(StatusProposta statusProposta);

}
