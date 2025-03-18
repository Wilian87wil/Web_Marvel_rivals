package com.wilian.test.marvel_rivals.config.convertersPropios;

import com.wilian.test.marvel_rivals.models.noMysql.Poder;
import com.wilian.test.marvel_rivals.models.noMysql.TypeAttack;
import org.bson.Document;
import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PowerReadConverter implements Converter<Document, Poder> {
    @Override
    public Poder convert(Document source) {
        Poder poder = new Poder();
        poder.setNombre(source.getString("nombre"));
        for (String llave:source.keySet()){
            if (llave.equalsIgnoreCase("_class")||llave.equalsIgnoreCase("nombre")||llave.equalsIgnoreCase("id")){
                continue;
            }
            Object o = source.get(llave);
            if (o instanceof List){
                List<?> list = (List<?>) o;
                List<TypeAttack> typeAttackList = new ArrayList<>();
                for (Object d:list){
                    Document document = (Document) d;
                    TypeAttack typeAttack = new TypeAttack();
                    typeAttack.setName(document.getString("name"));
                    typeAttack.setKey(document.getString("key"));
                    typeAttack.setUrl_image(document.getString("url_image"));
                    Object Lista = document.get("stats");
                    if (Lista instanceof List){
                        typeAttack.setStats((List<Map<String, Object>>) Lista);
                    }
                    typeAttackList.add(typeAttack);
                }
                poder.getExtra().put(llave,typeAttackList);
            }
        }
        return poder;
    }
}