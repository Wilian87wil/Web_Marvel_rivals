package com.wilian.test.marvel_rivals.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.wilian.test.marvel_rivals.config.convertersPropios.PoderReadConverter;
import com.wilian.test.marvel_rivals.config.convertersPropios.PoderWrintingConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class MongoConfigurer extends AbstractMongoClientConfiguration {
    @Override
    protected String getDatabaseName() {
        return "marvel_rivals";
    }

    @Override
    public MongoClient mongoClient() {
        return MongoClients.create("mongodb://localhost:27017");
    }

    @Override
    public MongoCustomConversions customConversions() {
        List<Converter<?,?>> converters = new ArrayList<>();
        converters.add(new PoderReadConverter());
        converters.add(new PoderWrintingConverter());
        return new MongoCustomConversions(converters);
    }
}
