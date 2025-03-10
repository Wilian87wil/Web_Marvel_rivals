package com.wilian.test.marvel_rivals.service;

import com.wilian.test.marvel_rivals.models.mySql.Heroe;
import com.wilian.test.marvel_rivals.models.mySql.respaldo.HeroeBorrados;
import com.wilian.test.marvel_rivals.models.noMysql.HeroePoder;
import com.wilian.test.marvel_rivals.repositorie.HeroeBorradosRepository;
import com.wilian.test.marvel_rivals.repositorie.HeroePoderReposity;
import com.wilian.test.marvel_rivals.repositorie.HeroeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
@Service
public class HeroeServicesImpl implements HeroeService{
    @Autowired
    private HeroeRepository repository;
    @Autowired
    private HeroePoderReposity poderReposity;
    @Autowired
    private HeroeBorradosRepository borradosRepository;

    @Override
    public Optional<Heroe> buscarPorId(Integer id) {
        Optional<Heroe> heroe = repository.findByIdFetchStats( id);
        Heroe heroe1 = heroe.get();
        Optional<HeroePoder> poder = poderReposity.findById(heroe1.getNombre());

        heroe1.setHeroePoder(poder.get());
        return Optional.ofNullable(heroe1);
    }

    @Override
    public Optional<Heroe> Editar(Integer id, Heroe heroe) {
        Optional<Heroe> heroe_recuperado = this.buscarPorId(id);
        if (heroe_recuperado.isPresent()) {
            Heroe heroe_existente = heroe_recuperado.get();
            heroe_existente.setNombre(heroe.getNombre());
            heroe_existente.setDescripcion(heroe.getDescripcion());
            heroe_existente.setLore(heroe.getLore());
            heroe_existente.setRol(heroe.getRol());
            heroe_existente.setStats(heroe.getStats());
            repository.save(heroe_existente);
            HeroePoder heroePoder_encontrado = poderReposity.findById(heroe_existente.getHeroePoder().getId()).get();
            HeroePoder heroePoder_nuevo = heroe.getHeroePoder();
            heroePoder_encontrado.setPoderes(heroePoder_nuevo.getPoderes());
            poderReposity.save(heroePoder_encontrado);
            heroe_existente.setHeroePoder(heroePoder_encontrado);
            return Optional.of(heroe_existente);
        }
        return Optional.empty();
    }

    @Override
    public Optional<String > eliminar(Integer id) {
        Optional<Heroe> heroe_optional = repository.findByIdFetchStats(id);
        if (heroe_optional.isPresent()){
            Heroe heroe = heroe_optional.get();
            Optional<HeroePoder> heroePoder = poderReposity.findById(heroe.getNombre());
            if (heroePoder.isPresent()){
                HeroePoder heroePoder1 = heroePoder.get();
                heroePoder1.setDeleted_at(new Date());
                heroePoder1.setEstado("desactivado");
                poderReposity.save(heroePoder1);
            }
            repository.deleteById(heroe.getId());
        }
        return Optional.ofNullable(heroe_optional.get().getNombre());
    }

    @Override
    public Optional<Heroe> recuperarId(Integer id) {
        Optional<HeroeBorrados> heroeBorrado = borradosRepository.findById(id);
        if (heroeBorrado.isPresent()){
            HeroeBorrados heroe = heroeBorrado.get();
            heroe.setAccion("RECUPERAR");
            Optional<Heroe> heroeOptional = recuperarHeroe(id, heroe);
            if (heroeOptional.isPresent()){
                return heroeOptional;
            }
        }
        return Optional.empty();
    }

    private Optional<Heroe> recuperarHeroe(Integer id, HeroeBorrados heroe) {
        borradosRepository.save(heroe);
        borradosRepository.deleteById(id);
        Optional<Heroe> heroe1 = repository.findByIdFetchStats(id);
        heroe1.ifPresent(heroe2 -> {
            Optional<HeroePoder> poder = poderReposity.findById(heroe2.getNombre());
            poder.ifPresent(heroePoder -> {
                heroePoder.setDeleted_at(null);
                heroePoder.setEstado("activo");
                poderReposity.save(heroePoder);
            });
        });
        return this.buscarPorId(id);
    }
}
