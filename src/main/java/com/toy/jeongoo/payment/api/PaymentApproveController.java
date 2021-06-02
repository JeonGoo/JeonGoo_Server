package com.toy.jeongoo.payment.api;

import com.toy.jeongoo.payment.service.PaymentApproveService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/payment")
@RequiredArgsConstructor
public class PaymentApproveController {

    private final PaymentApproveService paymentApproveService;

    @GetMapping("/approve")
    public void approve(@RequestParam(name = "pg_token") String pgToken) {
        try {
            paymentApproveService.approve(pgToken);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
