package com.wilian.test.marvel_rivals.repositorie;

import com.wilian.test.marvel_rivals.models.mySql.respaldo.UserBackup;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserDeletedRepository extends CrudRepository<UserBackup,Integer> {

    @Query("select ub from UserBackup ub where ub.nombre_respaldo= :nombre and ub.email_respaldo= :email")
    Optional<UserBackup> findUserBackup(@Param("nombre")String nombre, @Param("email")String email);

    @Transactional
    @Modifying
    @Query("delete from UserBackup ub where ub.nombre_respaldo= :nombre and ub.email_respaldo= :email")
    int DeleteUserBackup(@Param("nombre")String nombre, @Param("email")String email);
}
