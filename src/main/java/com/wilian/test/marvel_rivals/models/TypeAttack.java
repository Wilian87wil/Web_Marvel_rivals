package com.wilian.test.marvel_rivals.models;

import java.util.*;

public class TypeAttack {

    private String name;

    private String url_image;

    private String key;

    private List<Map<String, Object>> stats = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl_image() {
        return url_image;
    }

    public void setUrl_image(String url_image) {
        this.url_image = url_image;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<Map<String, Object>> getStats() {
        return stats;
    }

    public void setStats(List<Map<String, Object>> stats) {
        this.stats = stats;
    }
}
