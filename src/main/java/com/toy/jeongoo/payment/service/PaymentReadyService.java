package com.toy.jeongoo.payment.service;

import org.springframework.stereotype.Service;

@Service
public interface PaymentReadyService {

    String HOST = "https://kapi.kakao.com/v1/payment";

    String ready();

}
