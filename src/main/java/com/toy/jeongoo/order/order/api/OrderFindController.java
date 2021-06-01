package com.toy.jeongoo.order.order.api;

import com.toy.jeongoo.order.order.api.dto.response.OrderShowResponse;
import com.toy.jeongoo.order.order.model.Order;
import com.toy.jeongoo.order.order.repository.OrderRepository;
import com.toy.jeongoo.utils.DefaultResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static com.toy.jeongoo.order.util.ResponseMessage.FIND_ORDER;
import static com.toy.jeongoo.order.util.ResponseMessage.FIND_ORDER_FAIL;
import static com.toy.jeongoo.utils.StatusCode.BAD_REQUEST;
import static com.toy.jeongoo.utils.StatusCode.OK;

@Slf4j
@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderFindController {

    private final OrderRepository orderRepository;

    @GetMapping
    public DefaultResponse<List<OrderShowResponse>> showAll() {
        try {
            final List<Order> orderList = orderRepository.findAll();
            return DefaultResponse.res(OK, FIND_ORDER, toOrderShowResponseList(orderList));
        } catch (Exception e) {
            log.error(e.getMessage());
            return DefaultResponse.res(BAD_REQUEST, FIND_ORDER_FAIL);
        }
    }

    private List<OrderShowResponse> toOrderShowResponseList(List<Order> orderList) {
        return orderList.stream()
                .map(order -> new OrderShowResponse(order.getId(), order.getOrderLineList(),
                        order.getOrderer(), order.getShippingInfo()))
                .collect(Collectors.toList());
    }
}
