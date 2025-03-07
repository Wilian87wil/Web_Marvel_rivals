package com.wilian.test.marvel_rivals.models;

import org.bson.Document;
import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PoderReadConverter implements Converter<Document,Poder> {
    @Override
    public Poder convert(Document source) {
        Poder poder = new Poder();
        poder.setNombre(source.getString("nombre"));
        for (String llave:source.keySet()){
            if (llave.equalsIgnoreCase("nombre")||llave.equalsIgnoreCase("_id")||llave.equalsIgnoreCase("_class")){
                continue;
            }
            Object value = source.get(llave);
            if (value instanceof List){
                List<?> list= (List<?>) value;
                List<TypeAttack> typeAttacks = new ArrayList<>();
                for (Object o:list){
                    Document document = (Document) o;
                    TypeAttack typeAttack = new TypeAttack();
                    typeAttack.setName(document.getString("name"));
                    typeAttack.setUrl_image(document.getString("url_image"));
                    typeAttack.setKey(document.getString("key"));
                    Object object = document.get("stats");
                    if (object instanceof List){
                        typeAttack.setStats((List<Map<String, Object>>) object);
                    }
                    typeAttacks.add(typeAttack);
                }
            poder.getExtra().put(llave,typeAttacks);
            }
        }
        return poder;
    }
}
