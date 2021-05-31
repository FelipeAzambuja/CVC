package br.com.newbgp.cvc.repo;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.newbgp.cvc.model.TaxConfig;
@Repository 
public interface TaxConfigRepo extends CrudRepository<TaxConfig, Integer>, JpaSpecificationExecutor<TaxConfig> {

}
