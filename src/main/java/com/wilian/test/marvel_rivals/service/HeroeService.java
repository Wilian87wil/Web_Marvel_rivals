package com.wilian.test.marvel_rivals.service;

import com.wilian.test.marvel_rivals.models.mySql.Heroe;
import com.wilian.test.marvel_rivals.models.mySql.User;
import com.wilian.test.marvel_rivals.models.mySql.respaldo.UserBackup;

import java.util.List;
import java.util.Optional;

public interface HeroeService {
//METHODS HERO
    Optional<Heroe> findHeroId(Integer id);

    Optional<Heroe> editHero(Integer id , Heroe heroe);

    Optional<String> deletedHero(Integer id);

    Optional<Heroe> recoveryHero(Integer id);

    Optional<List<Heroe>> HerofindAll();
//METHOS USER
    Optional<User> EditUser(Integer id , User user);

    Optional<User> deletedUser(Integer id);

    Optional<UserBackup> recoverUserId(Integer id);

    Optional<User> findUser(String password, String email);

    Optional<User> registerUser(String name,String password,String email);
}
