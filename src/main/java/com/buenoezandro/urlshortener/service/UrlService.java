package com.buenoezandro.urlshortener.service;

import com.buenoezandro.urlshortener.entity.UrlEntity;
import com.buenoezandro.urlshortener.entity.exception.UrlNotFoundException;
import com.buenoezandro.urlshortener.repository.UrlRepository;
import org.springframework.stereotype.Service;

@Service
public class UrlService {
    private final UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public boolean existsById(String id) {
        return this.urlRepository.existsById(id);
    }

    public UrlEntity findById(String id) {
        return this.urlRepository.findById(id).orElseThrow(() -> new UrlNotFoundException("Url does not exist."));
    }

    public void saveUrl(UrlEntity urlEntity) {
        this.urlRepository.save(urlEntity);
    }
}