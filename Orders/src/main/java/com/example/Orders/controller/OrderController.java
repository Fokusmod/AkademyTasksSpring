package com.example.Orders.controller;

import com.example.Orders.service.OrderKafkaService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    private final OrderKafkaService orderKafkaService;

    public OrderController(OrderKafkaService orderKafkaService) {
        this.orderKafkaService = orderKafkaService;
    }

    @PostMapping
    public void getNewOrder(@RequestBody String orderInfo) {
        orderKafkaService.getNewOrder(orderInfo);
    }
}
