package com.wilian.test.marvel_rivals.controller;

import com.wilian.test.marvel_rivals.models.Heroe;
import com.wilian.test.marvel_rivals.service.HeroeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {
    @Autowired
    private HeroeService service;

    @GetMapping()
    public ResponseEntity<Heroe> buscarHeroe(){
        return ResponseEntity.ok(service.buscarPorId(1).get());
    }
}
