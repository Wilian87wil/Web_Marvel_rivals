package com.wilian.test.marvel_rivals.service;

import com.wilian.test.marvel_rivals.models.Heroe;
import com.wilian.test.marvel_rivals.models.HeroePoder;
import com.wilian.test.marvel_rivals.repositorie.HeroePoderReposity;
import com.wilian.test.marvel_rivals.repositorie.HeroeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serial;
import java.util.Optional;
@Service
public class HeroeServicesImpl implements HeroeService{
    @Autowired
    private HeroeRepository repository;
    @Autowired
    private HeroePoderReposity poderReposity;

    @Override
    public Optional<Heroe> buscarPorId(Integer id) {
        Optional<Heroe> heroe = repository.findById(id);
        Heroe heroe1 = heroe.get();
        Optional<HeroePoder> poder = poderReposity.findById(heroe1.getNombre());

        heroe1.setHeroePoder(poder.get());
        return Optional.ofNullable(heroe1);
    }
}
