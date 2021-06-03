package com.toy.jeongoo.payment.config;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class PaymentRestTemplateConfig {
    private static final int MAX_CONN_TOTAL = 100;
    private static final int MAX_CONN_PER_ROUTE = 50;
    private static final int READ_TIMEOUT = 3_000;
    private static final int CONNECTED_TIMEOUT = 3_000;

    @Bean
    public RestTemplate paymentRestTemplate() {
        final HttpClient httpClient = HttpClientBuilder.create()
                .setMaxConnTotal(MAX_CONN_TOTAL)
                .setMaxConnPerRoute(MAX_CONN_PER_ROUTE)
                .build();

        final HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
        requestFactory.setReadTimeout(READ_TIMEOUT);
        requestFactory.setConnectTimeout(CONNECTED_TIMEOUT);

        return new RestTemplate(requestFactory);
    }
}
