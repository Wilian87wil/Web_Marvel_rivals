package com.wilian.test.marvel_rivals.models.noMysql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Poder {

    private String nombre;

    private Map<String , List<TypeAttack>> extra=new HashMap<>();

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Map<String, List<TypeAttack>> getExtra() {
        return extra;
    }

    public void setExtra(Map<String, List<TypeAttack>> extra) {
        this.extra = extra;
    }
}
