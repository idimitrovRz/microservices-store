package com.ludogoriesoft.orderservice.service;

import com.ludogoriesoft.orderservice.dto.OrderRequest;
import com.ludogoriesoft.orderservice.model.Order;
import com.ludogoriesoft.orderservice.model.OrderItem;
import com.ludogoriesoft.orderservice.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;

    public void createOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderItem> orderItems = orderRequest.getOrderItemDtos()
                .stream()
                .map(orderItemDto -> modelMapper.map(orderItemDto, OrderItem.class))
                .toList();
        orderItems.forEach(orderItem -> orderItem.setOrder(order));
        order.setOrderItems(orderItems);
        orderRepository.save(order);
        log.info("Order {} is saved", order.getId());
    }
}
