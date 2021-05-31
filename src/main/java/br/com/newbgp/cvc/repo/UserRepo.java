package br.com.newbgp.cvc.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.newbgp.cvc.model.User;
@Repository
public interface UserRepo extends CrudRepository<User,Integer>, JpaSpecificationExecutor<User> {
    User findByEmailAndPassword(String email,String password);
    List<User> findByAccountContains(String account);
}
