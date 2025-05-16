package com.example.Orders.service;

import jakarta.annotation.PostConstruct;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderKafkaService {

    private final KafkaTemplate<String,String> kafkaTemplate;


    public OrderKafkaService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    //Получает информацию о новых заказах от пользователей. Отправляет информацию о заказе в тему Kafka "new_orders".
    // Поддерживает обновление статуса заказа.
    //Имитация получения миллиона запросов
    public void getNewOrder(String orderInfo) {
        System.out.println("Получен новый заказ " + orderInfo);
        for (int i = 0; i < 1000000; i++) {
            sendOrderInfoIntoKafka(orderInfo);
        }
    }

    private void sendOrderInfoIntoKafka(String info) {
        String data = "Информация о заказе: \n " + info;
        kafkaTemplate.send("new_orders", data);
    }
}
