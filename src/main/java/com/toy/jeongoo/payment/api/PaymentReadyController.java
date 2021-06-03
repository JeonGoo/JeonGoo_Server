package com.toy.jeongoo.payment.api;

import com.toy.jeongoo.payment.api.dto.request.PaymentReadyRequest;
import com.toy.jeongoo.payment.api.dto.response.PaymentReadyResponse;
import com.toy.jeongoo.payment.service.PaymentReadyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/payment")
@RequiredArgsConstructor
public class PaymentReadyController {

    private final PaymentReadyService paymentReadyService;

    @PostMapping("/ready")
    public String ready(@RequestBody PaymentReadyRequest readyRequest) {
        final PaymentReadyResponse readyResponse = paymentReadyService.ready(readyRequest);
        return "redirect:" + readyResponse.getNext_redirect_pc_url();
    }
}
