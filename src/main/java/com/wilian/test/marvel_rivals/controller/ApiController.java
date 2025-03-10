package com.wilian.test.marvel_rivals.controller;

import com.wilian.test.marvel_rivals.models.mySql.Heroe;
import com.wilian.test.marvel_rivals.service.HeroeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApiController {
    @Autowired
    private HeroeService service;

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Heroe> buscarHeroe(@PathVariable Integer id){
        return ResponseEntity.ok(service.buscarPorId(id).get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Heroe> editar(@RequestBody Heroe heroe, @PathVariable Integer id){
        return ResponseEntity.ok(service.Editar(id,heroe).get());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String > deleted(@PathVariable Integer id){
        return ResponseEntity.ok(service.eliminar(id).get());
    }
    @PutMapping("/recuperar/{id}")
    public ResponseEntity<Heroe> recuprar(@PathVariable Integer id){
        return ResponseEntity.ok(service.recuperarId(id).get());
    }
}
