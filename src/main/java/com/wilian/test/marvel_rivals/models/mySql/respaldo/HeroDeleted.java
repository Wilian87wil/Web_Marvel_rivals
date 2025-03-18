package com.wilian.test.marvel_rivals.models.mySql.respaldo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "heroes_respaldo")
public class HeroDeleted {

    @Id
    private Integer id_respaldo;

    private String nombre_respaldo;

    private String rol_respaldo;

    private String lore_respaldo;

    private String descripcion_respaldo;

    private Date fecha_eliminación;

    private String  accion;

    public Integer getId_respaldo() {
        return id_respaldo;
    }

    public void setId_respaldo(Integer id_respaldo) {
        this.id_respaldo = id_respaldo;
    }

    public String getNombre_respaldo() {
        return nombre_respaldo;
    }

    public void setNombre_respaldo(String nombre_respaldo) {
        this.nombre_respaldo = nombre_respaldo;
    }

    public String getRol_respaldo() {
        return rol_respaldo;
    }

    public void setRol_respaldo(String rol_respaldo) {
        this.rol_respaldo = rol_respaldo;
    }

    public String getLore_respaldo() {
        return lore_respaldo;
    }

    public void setLore_respaldo(String lore_respaldo) {
        this.lore_respaldo = lore_respaldo;
    }

    public String getDescripcion_respaldo() {
        return descripcion_respaldo;
    }

    public void setDescripcion_respaldo(String descripcion_respaldo) {
        this.descripcion_respaldo = descripcion_respaldo;
    }

    public Date getFecha_eliminación() {
        return fecha_eliminación;
    }

    public void setFecha_eliminación(Date fecha_eliminación) {
        this.fecha_eliminación = fecha_eliminación;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }
}
