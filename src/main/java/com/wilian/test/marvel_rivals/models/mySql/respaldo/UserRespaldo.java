package com.wilian.test.marvel_rivals.models.mySql.respaldo;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "user_respaldo")
public class UserRespaldo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer id_respaldo;

    private String nombre_respaldo;

    private String password_respaldo;

    private String email_respaldo;

    private String pasword_email_respaldo;

    private Date fecha_registro_eliminación;

    private String accion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getPassword_respaldo() {
        return password_respaldo;
    }

    public void setPassword_respaldo(String password_respaldo) {
        this.password_respaldo = password_respaldo;
    }

    public String getEmail_respaldo() {
        return email_respaldo;
    }

    public void setEmail_respaldo(String email_respaldo) {
        this.email_respaldo = email_respaldo;
    }

    public String getPasword_email_respaldo() {
        return pasword_email_respaldo;
    }

    public void setPasword_email_respaldo(String pasword_email_respaldo) {
        this.pasword_email_respaldo = pasword_email_respaldo;
    }

    public Date getFecha_registro_eliminación() {
        return fecha_registro_eliminación;
    }

    public void setFecha_registro_eliminación(Date fecha_registro_eliminación) {
        this.fecha_registro_eliminación = fecha_registro_eliminación;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }
}
