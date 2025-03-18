package com.wilian.test.marvel_rivals.repositorie;

import com.wilian.test.marvel_rivals.models.noMysql.HeroePoder;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HeroPowerRepository extends MongoRepository<HeroePoder,String> {
}
