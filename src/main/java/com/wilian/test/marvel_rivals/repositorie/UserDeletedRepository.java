package com.wilian.test.marvel_rivals.repositorie;

import com.wilian.test.marvel_rivals.models.mySql.respaldo.UserBackup;
import org.springframework.data.repository.CrudRepository;

public interface UserDeletedRepository extends CrudRepository<UserBackup,Integer> {
}
