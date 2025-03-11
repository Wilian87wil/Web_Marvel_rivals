package com.wilian.test.marvel_rivals.service;

import com.wilian.test.marvel_rivals.models.mySql.Heroe;
import com.wilian.test.marvel_rivals.models.mySql.User;

import java.util.Optional;

public interface HeroeService {

    Optional<Heroe> buscarPorId(Integer id);

    Optional<Heroe> Editar(Integer id ,Heroe heroe);

    Optional<String> eliminar(Integer id);

    Optional<Heroe> recuperarId(Integer id);

    Optional<User> buscarPorIdUser(Integer id);

    Optional<User> EditarUser(Integer id ,User user);

    Optional<User> eliminarUser(Integer id);

    Optional<User> recuperarIdUser(Integer id);
}
