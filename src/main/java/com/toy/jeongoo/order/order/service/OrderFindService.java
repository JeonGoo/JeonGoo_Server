package com.toy.jeongoo.order.order.service;

import com.toy.jeongoo.order.exception.OrderNotFoundException;
import com.toy.jeongoo.order.order.model.Order;
import com.toy.jeongoo.order.order.repository.OrderRepository;
import com.toy.jeongoo.order.order.repository.query.OrderQueryRepository;
import com.toy.jeongoo.user.service.UserFindService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderFindService {

    private final OrderRepository orderRepository;
    private final OrderQueryRepository orderQueryRepository;
    private final UserFindService userFindService;

    @Transactional(readOnly = true)
    public Order findOrder(Long orderId) {
        return findOrderById(orderId);
    }

    public List<Order> findAllOrderByOrderer(Long ordererId) {
        checkExistOrderer(ordererId);
        return orderQueryRepository.findByOrdererId(ordererId);
    }

    private void checkExistOrderer(Long ordererId) {
        userFindService.findUser(ordererId);
    }

    private Order findOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(String.format("not found order. orderId: %d", orderId)));
    }
}
