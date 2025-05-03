package com.example.AkademyTasks.service;

import com.example.AkademyTasks.dto.OrderDto;
import com.example.AkademyTasks.exception.NotFoundException;
import com.example.AkademyTasks.model.Order;
import com.example.AkademyTasks.model.Product;
import com.example.AkademyTasks.repository.OrderRepository;
import com.example.AkademyTasks.utils.JsonMapper;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional
    public String getCurrentOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Указанный заказ не найден"));
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderStatus(order.getOrderStatus());
        orderDto.setCustomerName(order.getCustomer().getFirstName() + " " + order.getCustomer().getLastName());
        orderDto.setOrderDate(order.getOrderDate());
        orderDto.setTotalPrice(order.getTotalPrice());
        orderDto.setShippingAddress(order.getShippingAddress());
        orderDto.setProducts(parseProductToJson(order.getProducts()));
        return JsonMapper.toJson(orderDto);
    }

    private List<String> parseProductToJson(List<Product> list) {
        return list.stream().map(JsonMapper::toJson).toList();
    }

    public ResponseEntity<String> createNewOrder(String json) {
        Order order = JsonMapper.toObject(json, Order.class);
        orderRepository.save(order);
        return ResponseEntity.status(HttpStatus.CREATED).body("Успешное довавление заказа");
    }
}
