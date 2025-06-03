package com.wilian.test.marvel_rivals.repositorie;

import com.wilian.test.marvel_rivals.models.mySql.Heroe;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface HeroRepository extends CrudRepository<Heroe,Integer> {

    @Query("SELECT h FROM Heroe h LEFT JOIN FETCH h.stats LEFT JOIN FETCH h.urls_img left join fetch h.color WHERE h.id = :id")
    Optional<Heroe> findByIdFetchStats(@Param("id") Integer id);

    @Query("SELECT DISTINCT  h FROM Heroe h LEFT JOIN FETCH h.stats LEFT JOIN FETCH h.urls_img LEFT JOIN FETCH h.color order by h.nombre ASC")
    List<Heroe> findAllFetch();
}
