package com.toy.jeongoo.payment.api;

import com.toy.jeongoo.payment.api.dto.request.PaymentReadyRequest;
import com.toy.jeongoo.payment.api.dto.response.PaymentReadyResponse;
import com.toy.jeongoo.payment.service.PaymentReadyService;
import com.toy.jeongoo.utils.DefaultResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import static com.toy.jeongoo.payment.util.ResponseMessage.*;
import static com.toy.jeongoo.utils.StatusCode.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/payment")
@RequiredArgsConstructor
public class PaymentReadyController {

    private final PaymentReadyService paymentReadyService;

    @PostMapping("/ready")
    public DefaultResponse<PaymentReadyResponse> ready(@RequestBody PaymentReadyRequest readyRequest) {
        try {
            final PaymentReadyResponse response = paymentReadyService.ready(readyRequest);
            return DefaultResponse.res(OK, PAYMENT_READY, response);
        } catch (Exception e) {
            log.error(e.getMessage());
            return DefaultResponse.res(BAD_REQUEST, PAYMENT_READY_FAIL);
        }
    }
}
