package com.wilian.test.marvel_rivals.repositorie;

import com.wilian.test.marvel_rivals.models.HeroePoder;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HeroePoderReposity extends MongoRepository<HeroePoder,String> {
}
