package com.example.AkademyTasks.service;

import com.example.AkademyTasks.model.User;
import com.example.AkademyTasks.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private final OrderStatusService orderStatusService;

    public OrderService(OrderRepository orderRepository, OrderStatusService orderStatusService) {
        this.orderRepository = orderRepository;
        this.orderStatusService = orderStatusService;
    }


}
