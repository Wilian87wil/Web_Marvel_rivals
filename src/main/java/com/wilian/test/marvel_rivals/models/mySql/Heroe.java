package com.wilian.test.marvel_rivals.models.mySql;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wilian.test.marvel_rivals.models.noMysql.HeroePoder;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "heroes")
public class Heroe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;

    private String rol;

    private String descripcion;

    private String lore;

    @OneToMany(mappedBy = "heroe", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnoreProperties("heroe")
    private List<Stats> stats;

    @Transient
    private HeroePoder heroePoder;

    public HeroePoder getHeroePoder() {
        return heroePoder;
    }

    public void setHeroePoder(HeroePoder heroePoder) {
        this.heroePoder = heroePoder;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getLore() {
        return lore;
    }

    public void setLore(String lore) {
        this.lore = lore;
    }

    public List<Stats> getStats() {
        return stats;
    }

    public void setStats(List<Stats> stats) {
        this.stats = stats;
    }
}
