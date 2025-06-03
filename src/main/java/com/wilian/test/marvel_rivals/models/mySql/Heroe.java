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

    private String url_image;

    private String url_video;

    @OneToMany(mappedBy = "heroe", fetch = FetchType.LAZY)
    @JsonIgnoreProperties("heroe")
    private List<Stats> stats;

    @Transient
    private HeroePoder heroePoder;

    @OneToOne(mappedBy = "heroe",fetch = FetchType.LAZY)
    @JsonIgnoreProperties("heroes")
    private Img urls_img;

    @OneToOne(mappedBy = "heroe",fetch = FetchType.LAZY)
    @JsonIgnoreProperties("heroes")
    private Color color;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getUrl_video() {
        return url_video;
    }

    public void setUrl_video(String url_video) {
        this.url_video = url_video;
    }

    public String getUrl_image() {
        return url_image;
    }

    public void setUrl_image(String url_image) {
        this.url_image = url_image;
    }

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

    public Img getUrls_img() {
        return urls_img;
    }

    public void setUrls_img(Img urls_img) {
        this.urls_img = urls_img;
    }
}
