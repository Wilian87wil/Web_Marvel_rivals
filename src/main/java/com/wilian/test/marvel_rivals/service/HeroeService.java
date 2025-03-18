package com.wilian.test.marvel_rivals.service;

import com.wilian.test.marvel_rivals.models.mySql.Heroe;
import com.wilian.test.marvel_rivals.models.mySql.User;

import java.util.Optional;

public interface HeroeService {

    Optional<Heroe> findHeroId(Integer id);

    Optional<Heroe> editHero(Integer id , Heroe heroe);

    Optional<String> deletedHero(Integer id);

    Optional<Heroe> recoveryHero(Integer id);

    Optional<User> EditUser(Integer id , User user);

    Optional<User> deletedUser(Integer id);

    Optional<User> recoverUserId(Integer id);

    Optional<User> findUser(String nombre, String password, String email);
}
