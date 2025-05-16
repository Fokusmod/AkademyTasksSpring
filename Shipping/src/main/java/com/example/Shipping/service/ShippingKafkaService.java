package com.example.Shipping.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ShippingKafkaService {

    private final KafkaTemplate<String,String> kafkaTemplate;

    public ShippingKafkaService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    //Получает информацию о заказах, которые были оплачены, из темы "payed_orders". Осуществляет процесс упаковки
    // и отправки товара. Отправляет информацию об успешной отгрузке в тему Kafka "sent_orders".

    @KafkaListener(topics = "payed_orders", groupId = "orderGroup")
    public void listenPayedOrdersTopic(String message) {
        System.out.println(message);
        System.out.println("Упаковка и отправка товара.");
        sendSuccessShippingInfo("Успешная отгрузка товара");
    }

    private void sendSuccessShippingInfo(String info) {
        kafkaTemplate.send("sent_orders", info);
    }
}
