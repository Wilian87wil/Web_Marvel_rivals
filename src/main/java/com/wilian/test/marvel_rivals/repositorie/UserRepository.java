package com.wilian.test.marvel_rivals.repositorie;

import com.wilian.test.marvel_rivals.models.mySql.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

}
