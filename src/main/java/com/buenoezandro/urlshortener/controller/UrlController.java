package com.buenoezandro.urlshortener.controller;

import com.buenoezandro.urlshortener.controller.dto.ShortenUrlRequest;
import com.buenoezandro.urlshortener.controller.dto.ShortenUrlResponse;
import com.buenoezandro.urlshortener.entity.UrlEntity;
import com.buenoezandro.urlshortener.service.UrlService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;

@RestController
public class UrlController {
    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping(value = "/shorten-url")
    public ResponseEntity<ShortenUrlResponse> shortenUrl(@RequestBody ShortenUrlRequest shortenUrlRequest, HttpServletRequest httpServletRequest) {
        String id;

        do {
            id = RandomStringUtils.randomAlphanumeric(5, 10);
        } while (this.urlService.existsById(id));

        this.urlService.saveUrl(new UrlEntity(id, shortenUrlRequest.url(), LocalDateTime.now().plusMinutes(1)));

        String baseUrl = httpServletRequest.getRequestURL().toString().replace(httpServletRequest.getRequestURI(), "");
        String redirectUrl = baseUrl + "/" + id;

        return ResponseEntity.ok(new ShortenUrlResponse(redirectUrl));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Void> redirect(@PathVariable(value = "id") String id) {
        var url = this.urlService.findById(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(url.getFullUrl()));

        return ResponseEntity.status(HttpStatus.FOUND).headers(headers).build();
    }
}