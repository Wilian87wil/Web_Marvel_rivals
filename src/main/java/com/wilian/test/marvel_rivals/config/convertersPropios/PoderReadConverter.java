package com.wilian.test.marvel_rivals.config.convertersPropios;

import com.wilian.test.marvel_rivals.models.noMysql.Poder;
import com.wilian.test.marvel_rivals.models.noMysql.TypeAttack;
import org.bson.Document;
import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PoderReadConverter implements Converter<Document, Poder> {


    @Override
    public Poder convert(Document source) {
        Poder poder = new Poder();
        poder.setNombre(source.getString("nombre"));
        for (String llave:source.keySet()){
            Object object = source.get(llave);
            if (object instanceof List){
                List<Document> documentList = (List<Document>) object;
                List<TypeAttack> typeAttackList = new ArrayList<>();
                for (Document d:documentList){
                    TypeAttack typeAttack= new TypeAttack();
                    typeAttack.setName(d.getString("name"));
                    typeAttack.setKey(d.getString("key"));
                    typeAttack.setUrl_image(d.getString("url_image"));
                    Object object1 = d.get("stats");
                    if (object1 instanceof List){
                        typeAttack.setStats((List<Map<String, Object>>) object1);
                    }
                    typeAttackList.add(typeAttack);
                }
                poder.getExtra().put(llave,typeAttackList);
            }
        }
        return poder;
    }
}
