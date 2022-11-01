package com.my.testproject.model.persistenceLayer;

import com.my.testproject.model.businessLayer.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}
