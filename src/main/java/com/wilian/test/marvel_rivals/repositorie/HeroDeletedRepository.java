package com.wilian.test.marvel_rivals.repositorie;

import com.wilian.test.marvel_rivals.models.mySql.respaldo.HeroDeleted;
import org.springframework.data.repository.CrudRepository;

public interface HeroDeletedRepository extends CrudRepository<HeroDeleted, Integer> {
}
