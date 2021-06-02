package com.toy.jeongoo.payment.service;

import com.toy.jeongoo.payment.api.dto.response.PaymentApproveResponse;
import com.toy.jeongoo.payment.properties.PaymentProperties;
import com.toy.jeongoo.payment.repository.RestTemplateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentApproveService {

    private static final String HOST = "https://kapi.kakao.com/v1/payment/approve";

    private final PaymentProperties paymentProperties;
    private final RestTemplateRepository<PaymentApproveResponse> restTemplateRepository;

    public PaymentApproveResponse approve(String pgToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + paymentProperties.getAuth());
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.add("Accept", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=utf-8");

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("cid", "TC0ONETIME");
        requestBody.add("tid", "testTid");
        requestBody.add("partner_order_id", "1");
        requestBody.add("partner_user_id", "1");
        requestBody.add("pg_token", pgToken);
        requestBody.add("total_amount", "200000");

        return restTemplateRepository
                .post(HOST, headers, requestBody, PaymentApproveResponse.class)
                .getBody();
    }
}
