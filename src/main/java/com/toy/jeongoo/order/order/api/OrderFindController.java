package com.toy.jeongoo.order.order.api;

import com.toy.jeongoo.order.exception.OrderNotFoundException;
import com.toy.jeongoo.order.order.api.dto.response.OrderShowResponse;
import com.toy.jeongoo.order.order.model.Order;
import com.toy.jeongoo.order.order.repository.OrderRepository;
import com.toy.jeongoo.order.order.repository.query.OrderQueryRepository;
import com.toy.jeongoo.order.order.service.OrderFindService;
import com.toy.jeongoo.utils.DefaultResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    private final OrderFindService orderFindService;

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

    @GetMapping("/{orderId}")
    public DefaultResponse<OrderShowResponse> showOrder(@PathVariable Long orderId) {
        try {
            final Order order = orderFindService.findOrder(orderId);
            return DefaultResponse.res(OK, FIND_ORDER, toOrderShowResponse(order));
        } catch (OrderNotFoundException e) {
            log.error(e.getMessage());
            return DefaultResponse.res(BAD_REQUEST, FIND_ORDER_FAIL);
        }
    }

    @GetMapping("/orderer/{ordererId}")
    public DefaultResponse<List<OrderShowResponse>> showOrderByOrderer(@PathVariable Long ordererId) {
        try {
            final List<Order> orderList = orderFindService.findAllOrderByOrderer(ordererId);
            return DefaultResponse.res(OK, FIND_ORDER, toOrderShowResponseList(orderList));
        } catch (OrderNotFoundException e) {
            log.error(e.getMessage());
            return DefaultResponse.res(BAD_REQUEST, FIND_ORDER_FAIL);
        }
    }

    private List<OrderShowResponse> toOrderShowResponseList(List<Order> orderList) {
        return orderList.stream()
                .map(this::toOrderShowResponse)
                .collect(Collectors.toList());
    }

    private OrderShowResponse toOrderShowResponse(Order order) {
        return new OrderShowResponse(order.getId(), order.getOrderLineList(), order.getOrderer(), order.getShippingInfo());
    }
}
