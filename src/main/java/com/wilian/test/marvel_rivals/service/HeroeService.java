package com.wilian.test.marvel_rivals.service;

import com.wilian.test.marvel_rivals.models.Heroe;
import com.wilian.test.marvel_rivals.repositorie.HeroeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface HeroeService {

    Optional<Heroe> buscarPorId(Integer id);
}
