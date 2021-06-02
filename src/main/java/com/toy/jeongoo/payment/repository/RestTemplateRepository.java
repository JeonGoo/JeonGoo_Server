package com.toy.jeongoo.payment.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@Repository
@RequiredArgsConstructor
public class RestTemplateRepository<T> {

    private final RestTemplate restTemplate;

    public ResponseEntity<T> get(String url, HttpHeaders httpHeaders) {
        return call(url, GET, httpHeaders, null, (Class<T>) Object.class);
    }

    public ResponseEntity<T> get(String url, HttpHeaders httpHeaders, Class<T> response) {
        return call(url, GET, httpHeaders, null, response);
    }

    public ResponseEntity<T> post(String url, HttpHeaders httpHeaders, Class<T> response) {
        return call(url, POST, httpHeaders, null, response);
    }

    public ResponseEntity<T> post(String url, HttpHeaders httpHeaders, Object requestBody, Class<T> response) {
        return call(url, POST, httpHeaders, requestBody, response);
    }

    private ResponseEntity<T> call(String url, HttpMethod httpMethod, HttpHeaders httpHeaders, Object requestBody, Class<T> response) {
        return restTemplate.exchange(url, httpMethod, new HttpEntity<>(requestBody, httpHeaders), response);
    }
}
