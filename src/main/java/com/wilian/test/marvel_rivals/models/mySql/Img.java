package com.wilian.test.marvel_rivals.models.mySql;

import jakarta.persistence.*;

@Entity
@Table(name = "img")
public class Img {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String fondo_url;

    private String personaje_url;

    private String logo_url;

    private String logo_rol_url;

    private String url_img_logo_pj;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private Heroe heroe;

    public String getFondo_url() {
        return fondo_url;
    }

    public void setFondo_url(String fondo_url) {
        this.fondo_url = fondo_url;
    }

    public String getPersonaje_url() {
        return personaje_url;
    }

    public void setPersonaje_url(String personaje_url) {
        this.personaje_url = personaje_url;
    }

    public String getLogo_url() {
        return logo_url;
    }

    public void setLogo_url(String logo_url) {
        this.logo_url = logo_url;
    }

    public String getLogo_rol_url() {
        return logo_rol_url;
    }

    public void setLogo_rol_url(String logo_rol_url) {
        this.logo_rol_url = logo_rol_url;
    }

    public String getUrl_img_logo_pj() {
        return url_img_logo_pj;
    }

    public void setUrl_img_logo_pj(String url_img_logo_pj) {
        this.url_img_logo_pj = url_img_logo_pj;
    }
}
