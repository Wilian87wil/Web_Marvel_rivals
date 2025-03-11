package com.wilian.test.marvel_rivals.repositorie;

import com.wilian.test.marvel_rivals.models.mySql.respaldo.UserRespaldo;
import org.springframework.data.repository.CrudRepository;

public interface UserBorradoRepository extends CrudRepository<UserRespaldo,Integer> {
}
