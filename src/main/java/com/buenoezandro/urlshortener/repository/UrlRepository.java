package com.buenoezandro.urlshortener.repository;

import com.buenoezandro.urlshortener.entity.UrlEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UrlRepository extends MongoRepository<UrlEntity, String> {
}