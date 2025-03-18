package com.wilian.test.marvel_rivals.models.noMysql;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(collection = "heroes_poderes")
@TypeAlias("heroePoder")
public class HeroePoder {

    @Id
    @JsonIgnore
    private String id;

    private List<Poder> powers =new ArrayList<>();

    @JsonIgnore
    @Field(write = Field.Write.ALWAYS)
    private Date deleted_at;

    @JsonIgnore
    private String state;

    public Date getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(Date deleted_at) {
        this.deleted_at = deleted_at;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Poder> getPowers() {
        return powers;
    }

    public void setPowers(List<Poder> powers) {
        this.powers = powers;
    }
}
