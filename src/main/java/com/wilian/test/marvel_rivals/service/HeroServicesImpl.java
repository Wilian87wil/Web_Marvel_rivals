package com.wilian.test.marvel_rivals.service;

import com.wilian.test.marvel_rivals.models.mySql.Heroe;
import com.wilian.test.marvel_rivals.models.mySql.User;
import com.wilian.test.marvel_rivals.models.mySql.respaldo.HeroDeleted;
import com.wilian.test.marvel_rivals.models.mySql.respaldo.UserBackup;
import com.wilian.test.marvel_rivals.models.noMysql.HeroePoder;
import com.wilian.test.marvel_rivals.repositorie.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
public class HeroServicesImpl implements HeroeService{
    @Autowired
    private HeroRepository heroRepository;
    @Autowired
    private HeroPowerRepository powerRepository;
    @Autowired
    private HeroDeletedRepository deletedRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserDeletedRepository userDeletedRepository;

    //USER METHODS

    @Override
    public Optional<User> findUser(String password, String email) {
        Optional<User> user = userRepository.buscarUsuario(password, email);
        if (user.isPresent()){
            System.out.println("Se encontro");
            return user;
        }else {
            System.out.println("No se encontro");
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> registerUser(String name,String password,String email){
        User user = new User();
          if (userRepository.DebugUserName(name).isPresent()){
              user.setNombre("Ya existe el nombre que colocaste, utiliza otro porfavor");
              user.setPassword(password);
              user.setEmail(email);
              return Optional.of(user);
          } else if (userRepository.DebugUserEmail(email).isPresent()) {
              user.setEmail("Ya existe el email que colocaste, utiliza otro porfavor");
              user.setNombre(name);
              user.setPassword(password);
              return Optional.of(user);
          }
        user= new User();
        user.setNombre(name);
        user.setPassword(password);
        user.setEmail(email);
        user.setFecha_registro(new Date())  ;
        userRepository.save(user);
        return userRepository.buscarUsuario(user.getPassword(),user.getEmail());
    }

    @Override
    public Optional<User> recoverUser(String name, String email) {
        Optional<UserBackup> userBackup = userDeletedRepository.findUserBackup(name, email);
        if (userBackup.isPresent()){
            UserBackup backup = userBackup.get();
            backup.setAccion("RECUPERAR");
            userDeletedRepository.save(backup);
        }else {
            return Optional.empty();
        }
        userDeletedRepository.DeleteUserBackup(name,email);

        return userRepository.BuscarUserNameEmail(name, email);

    }

    @Override
    public Optional<User> EditUser(Integer id, User user) {
        Optional<User> UserFound = userRepository.findById(id);
        if (UserFound.isPresent()){
            User user1 = UserFound.get();
            user1.setNombre(user.getNombre());
            user1.setEmail(user.getEmail());
            user1.setPassword(user.getPassword());
            user1.setFecha_registro(user.getFecha_registro());
            userRepository.save(user1);
            return Optional.of(user1);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> deletedUser(Integer id) {
        Optional<User> userfind = userRepository.findById(id);
        if (userfind.isPresent()){
            userRepository.deleteById(id);
            return userfind;
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findUser(Integer id) {
        return userRepository.findById(id);
    }


    //HERO METHODS
    @Override
    public List<Heroe> HerofindAll() {
        List<Heroe> heroes = heroRepository.findAllFetch();
        heroes.forEach(h->{
            Optional<HeroePoder> poder = powerRepository.findById(h.getNombre());
            poder.ifPresent(h::setHeroePoder);
        });
        return heroes;
    }


    @Override
    public Optional<Heroe> findHeroId(Integer id) {
        Optional<Heroe> heroe = heroRepository.findByIdFetchStats(id);
        Heroe heroe1 = heroe.get();
        Optional<HeroePoder> poder = powerRepository.findById(heroe1.getNombre());
        heroe1.setHeroePoder(poder.get());
        return Optional.ofNullable(heroe1);
    }

    @Override
    public Optional<Heroe> editHero(Integer id, Heroe heroe) {
        Optional<Heroe> heroe_recuperado = this.findHeroId(id);
        if (heroe_recuperado.isPresent()) {
            Heroe heroe_existente = heroe_recuperado.get();
            heroe_existente.setNombre(heroe.getNombre());
            heroe_existente.setDescripcion(heroe.getDescripcion());
            heroe_existente.setLore(heroe.getLore());
            heroe_existente.setRol(heroe.getRol());
            heroe_existente.setStats(heroe.getStats());
            heroRepository.save(heroe_existente);
            HeroePoder heroePoder_encontrado = powerRepository.findById(heroe_existente.getHeroePoder().getId()).get();
            HeroePoder heroePoder_nuevo = heroe.getHeroePoder();
            heroePoder_encontrado.setPoderes(heroePoder_nuevo.getPoderes());
            powerRepository.save(heroePoder_encontrado);
            heroe_existente.setHeroePoder(heroePoder_encontrado);
            return Optional.of(heroe_existente);
        }
        return Optional.empty();
    }

    @Override
    public Optional<String > deletedHero(Integer id) {
        Optional<Heroe> heroe_optional = heroRepository.findByIdFetchStats(id);
        if (heroe_optional.isPresent()){
            Heroe heroe = heroe_optional.get();
            Optional<HeroePoder> heroePoder = powerRepository.findById(heroe.getNombre());
            if (heroePoder.isPresent()){
                HeroePoder heroePoder1 = heroePoder.get();
                heroePoder1.setDeleted_at(new Date());
                heroePoder1.setState("desactivado");
                powerRepository.save(heroePoder1);
            }
            heroRepository.deleteById(heroe.getId());
        }
        return Optional.ofNullable(heroe_optional.get().getNombre());
    }

    @Override
    public Optional<Heroe> recoveryHero(Integer id) {
        Optional<HeroDeleted> heroeBorrado = deletedRepository.findById(id);
        if (heroeBorrado.isPresent()){
            HeroDeleted heroe = heroeBorrado.get();
            heroe.setAccion("RECUPERAR");
            Optional<Heroe> heroeOptional = recoveryHeroPoder(id, heroe);
            if (heroeOptional.isPresent()){
                return heroeOptional;
            }
        }
        return Optional.empty();
    }

    //METHODS EXTRAS

    private Optional<Heroe> recoveryHeroPoder(Integer id, HeroDeleted heroe) {
        deletedRepository.save(heroe);
        deletedRepository.deleteById(id);
        Optional<Heroe> heroe1 = heroRepository.findByIdFetchStats(id);
        heroe1.ifPresent(heroe2 -> {
            Optional<HeroePoder> poder = powerRepository.findById(heroe2.getNombre());
            poder.ifPresent(heroePoder -> {
                heroePoder.setDeleted_at(null);
                heroePoder.setState("activo");
                powerRepository.save(heroePoder);
            });
        });
        return this.findHeroId(id);
    }
}
