package com.toy.jeongoo.order.order.service;

import com.toy.jeongoo.order.order.api.dto.request.OrderLineRequest;
import com.toy.jeongoo.order.order.api.dto.request.OrderRequest;
import com.toy.jeongoo.order.order.api.dto.request.ShippingInfoRequest;
import com.toy.jeongoo.order.order.model.Order;
import com.toy.jeongoo.order.order.model.OrderLine;
import com.toy.jeongoo.order.order.model.Orderer;
import com.toy.jeongoo.order.order.model.ShippingInfo;
import com.toy.jeongoo.order.order.repository.OrderRepository;
import com.toy.jeongoo.order.product.model.OrderProduct;
import com.toy.jeongoo.product.model.Product;
import com.toy.jeongoo.product.service.ProductFindService;
import com.toy.jeongoo.user.model.Address;
import com.toy.jeongoo.user.model.User;
import com.toy.jeongoo.user.service.UserFindService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductFindService productFindService;
    private final UserFindService userFindService;

    @Transactional
    public Long order(OrderRequest orderRequest) {
        final Order order = new Order(toOrderLineList(orderRequest.getOrderLineRequestList()), toShippingInfo(orderRequest.getShippingInfoRequest()));
        orderRepository.save(order);

        // payment event handler
        return order.getId();
    }

    private List<OrderLine> toOrderLineList(List<OrderLineRequest> orderLineRequestList) {
        return orderLineRequestList.stream()
                .map(ol -> new OrderLine(toProduct(ol), ol.getQuantity()))
                .collect(Collectors.toList());
    }

    private OrderProduct toProduct(OrderLineRequest orderLineRequest) {
        final Product product = productFindService.findProduct(orderLineRequest.getProductId());
        return new OrderProduct(product.getId(), product.getName(), product.getPrice());
    }

    private ShippingInfo toShippingInfo(ShippingInfoRequest shippingInfoRequest) {
        final Address address = new Address(shippingInfoRequest.getAddressDto().getCity(), shippingInfoRequest.getAddressDto().getDetailed());

        final User user = userFindService.findUser(shippingInfoRequest.getOrdererId());
        final Orderer orderer = new Orderer(user.getId(), user.getName(), user.getPhoneNumber());
        return new ShippingInfo(address, orderer);
    }
}
