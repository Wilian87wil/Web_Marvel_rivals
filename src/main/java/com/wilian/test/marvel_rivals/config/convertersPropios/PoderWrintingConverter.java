package com.wilian.test.marvel_rivals.config.convertersPropios;

import com.wilian.test.marvel_rivals.models.noMysql.Poder;
import com.wilian.test.marvel_rivals.models.noMysql.TypeAttack;
import org.springframework.core.convert.converter.Converter;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PoderWrintingConverter implements Converter<Poder,Document> {

    @Override
    public Document convert(Poder source) {
        Document d = new Document();
        d.put("nombre",source.getNombre());
        for (Map.Entry<String ,List<TypeAttack>> entry:source.getExtra().entrySet()){
            List<Document> documentList = new ArrayList<>();
            for (TypeAttack t: entry.getValue()){
                Document doc = new Document();
                doc.put("name",t.getName());
                doc.put("key",t.getKey());
                doc.put("url_image",t.getUrl_image());
                doc.put("stats",t.getStats());
                documentList.add(doc);
            }
            d.put(entry.getKey(),documentList);
        }
        return d;
    }
}
