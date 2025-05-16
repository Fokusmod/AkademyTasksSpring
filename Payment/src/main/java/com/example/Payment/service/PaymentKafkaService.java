package com.example.Payment.service;

import jakarta.annotation.PostConstruct;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PaymentKafkaService {

    private final KafkaTemplate<String,String> kafkaTemplate;

    private static int count = 3;


    public PaymentKafkaService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    //Получает информацию о новых заказах из темы "new_orders". Обрабатывает оплату заказа. Отправляет информацию об
    // успешной оплате в тему Kafka "payed_orders".
    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "orderGroup", concurrency = "${spring.kafka.consumer.concurrency}")
    public void listenNewOrderInfo(String orderInfo) throws IllegalAccessException {
        //Эмуляция ошибок. приводит в повторным попыткам обработать запрос:
        //[Consumer clientId=consumer-orderGroup-1, groupId=orderGroup] Seeking to offset 11 for partition new_orders-0
        //2025-05-16T16:18:12.860+03:00  INFO 33244 --- [Payment] [ntainer#0-0-C-1] o.s.k.l.KafkaMessageListenerContainer
        //Record in retry and not yet recovered
        if (count == 0) {
            System.out.println(orderInfo);
            System.out.println("handle payment");
            sendSuccessPaymentInfo("Успешная оплата заказа");
            count = 3;
        } else {
            count--;
            throw new IllegalAccessException("Кол-во попыток больше нуля");
        }
    }

    private void sendSuccessPaymentInfo(String info) {
        kafkaTemplate.send("payed_orders", info);
    }

}
