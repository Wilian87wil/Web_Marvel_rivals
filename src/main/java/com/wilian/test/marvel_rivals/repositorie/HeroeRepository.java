package com.wilian.test.marvel_rivals.repositorie;

import com.wilian.test.marvel_rivals.models.mySql.Heroe;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface HeroeRepository extends CrudRepository<Heroe,Integer> {

    @Query("SELECT h FROM Heroe h LEFT JOIN FETCH h.stats WHERE h.id = :id")
    Optional<Heroe> findByIdFetchStats(@Param("id") Integer id);
}
