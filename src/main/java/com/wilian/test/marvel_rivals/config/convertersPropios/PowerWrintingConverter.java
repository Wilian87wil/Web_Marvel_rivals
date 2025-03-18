package com.wilian.test.marvel_rivals.config.convertersPropios;

import com.wilian.test.marvel_rivals.models.noMysql.Poder;
import com.wilian.test.marvel_rivals.models.noMysql.TypeAttack;
import org.springframework.core.convert.converter.Converter;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PowerWrintingConverter implements Converter<Poder,Document> {
    @Override
    public Document convert(Poder source) {
        Document document = new Document();
        document.put("nombre",source.getNombre());
        for (Map.Entry<String,List<TypeAttack>> llave:source.getExtra().entrySet()){
            List<Document> documentList = new ArrayList<>();
            for (TypeAttack t:llave.getValue()){
                Document d = new Document();
                d.put("name",t.getName());
                d.put("key",t.getKey());
                d.put("url_image",t.getUrl_image());
                d.put("stats",t.getStats());
                documentList.add(d);
            }
            document.put(llave.getKey(),documentList);
        }
        return document;
    }
}