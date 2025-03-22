package com.wilian.test.marvel_rivals.repositorie;

import com.wilian.test.marvel_rivals.models.mySql.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

    @Query("select u from User u where u.password= :password and u.email= :email")
    Optional<User> buscarUsuario(@Param("password") String password,@Param("email") String email);

    @Query("select u from User u where u.nombre= :nombre")
    Optional<User> DebugUserName(@Param("nombre")String nombre);

    @Query("select u from User u where u.email= :email")
    Optional<User> DebugUserEmail(@Param("email")String email);

}
