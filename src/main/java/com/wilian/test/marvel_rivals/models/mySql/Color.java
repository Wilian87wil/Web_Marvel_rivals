package com.wilian.test.marvel_rivals.models.mySql;

import jakarta.persistence.*;

@Entity
@Table(name = "colors")
public class Color {

    @Id
    private Integer id;

    private String color_1;

    private String color_2;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private Heroe heroe;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getColor_1() {
        return color_1;
    }

    public void setColor_1(String color_1) {
        this.color_1 = color_1;
    }

    public String getColor_2() {
        return color_2;
    }

    public void setColor_2(String color_2) {
        this.color_2 = color_2;
    }
}
