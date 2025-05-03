package com.example.AkademyTasks.controller;

import com.example.AkademyTasks.service.OrderService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@Validated
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @GetMapping("/order/{id}")
    public String getCurrentOrder(@PathVariable Long id) {
        return orderService.getCurrentOrder(id);
    }

    @PostMapping("/order")
    public ResponseEntity<String> createNewOrder(@PathVariable @NotBlank String json) {
        return orderService.createNewOrder(json);
    }

}
