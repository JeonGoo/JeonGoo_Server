package com.toy.jeongoo.order.order.api;

import com.toy.jeongoo.order.order.api.dto.request.OrderRequest;
import com.toy.jeongoo.order.order.service.OrderService;
import com.toy.jeongoo.utils.DefaultResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.toy.jeongoo.order.util.ResponseMessage.CREATE_ORDER;
import static com.toy.jeongoo.order.util.ResponseMessage.CREATE_ORDER_FAIL;
import static com.toy.jeongoo.utils.StatusCode.BAD_REQUEST;
import static com.toy.jeongoo.utils.StatusCode.CREATED;

@Slf4j
@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/order")
    public DefaultResponse<Long> order(@RequestBody OrderRequest orderRequest) {
        try {
            Long orderId = orderService.order(orderRequest);
            return DefaultResponse.res(CREATED, CREATE_ORDER, orderId);
        } catch (Exception e) {
            log.error(e.getMessage());
            return DefaultResponse.res(BAD_REQUEST, CREATE_ORDER_FAIL);
        }
    }

}
