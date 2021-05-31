package br.com.newbgp.cvc.repo;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.newbgp.cvc.model.Transfers;
import br.com.newbgp.cvc.model.User;

@Repository
public interface TransfersRepo extends CrudRepository<Transfers, Integer>, JpaSpecificationExecutor<Transfers> {

	List<Transfers> findByOrigin(User origin);

	List<Transfers> findByDestiny(User destiny);


}
