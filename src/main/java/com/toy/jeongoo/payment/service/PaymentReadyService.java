package com.toy.jeongoo.payment.service;

import com.toy.jeongoo.payment.api.dto.request.PaymentReadyProductDto;
import com.toy.jeongoo.payment.api.dto.request.PaymentReadyRequest;
import com.toy.jeongoo.payment.api.dto.response.PaymentReadyResponse;
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
public class PaymentReadyService {
    private static final String HOST = "https://kapi.kakao.com/v1/payment/ready";
    private static final String APPROVAL_URL = "http://localhost:8080/api/v1/payment/success";
    private static final String CANCEL_URL = "http://http://15.164.90.61:8080/v1/payment/cancel";
    private static final String FAIL_URL = "http://http://15.164.90.61:8080/v1/payment/fail";

    private final PaymentProperties paymentProperties;

    private final RestTemplateRepository<PaymentReadyResponse> restTemplateRepository;

    public PaymentReadyResponse ready(PaymentReadyRequest readyRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + paymentProperties.getAuth());
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.add("Accept", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=utf-8");

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        final PaymentReadyProductDto product = readyRequest.getProductDto();
        requestBody.add("cid", readyRequest.getCid());
        requestBody.add("partner_order_id", readyRequest.getOrderId());
        requestBody.add("partner_user_id", readyRequest.getUserId());
        requestBody.add("item_name", product.getName());
        requestBody.add("quantity", String.valueOf(product.getQuantity()));
        requestBody.add("total_amount", String.valueOf(product.getTotalAmount()));
        requestBody.add("tax_free_amount", readyRequest.getTaxFreeAmount());
        requestBody.add("approval_url", APPROVAL_URL);
        requestBody.add("cancel_url", CANCEL_URL);
        requestBody.add("fail_url", FAIL_URL);

        return restTemplateRepository
                .post(HOST, headers, requestBody, PaymentReadyResponse.class)
                .getBody();
    }
}
