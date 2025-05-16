package com.example.Notifications.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationKafkaService {

    //Подписывается на тему "sent_orders". Отправляет уведомления пользователям о том, что их заказ был успешно
    // доставлен.
    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "orderGroup", concurrency = "${spring.kafka.consumer.concurrency}")
    public void listenSentOrdersTopic(String message) {
        System.out.println(message);
        System.out.println("Посылаем уведомление пользователю об успешной доставке.");
    }
}
