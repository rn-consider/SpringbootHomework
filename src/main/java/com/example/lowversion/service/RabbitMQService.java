package com.example.lowversion.service;

import com.example.lowversion.entity.Person;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQService{
    @RabbitListener(bindings =@QueueBinding(value = @Queue("routing_queue_error"),exchange = @Exchange(value = "routing_exchange"
            ,type = "direct"),
            key = "error_routing_key"))
    public void routingConsumerError(String message) {
        System.out.println("接收到error级别日志消息： "+message);
    }
    @RabbitListener(bindings =@QueueBinding(value = @Queue("routing_queue_all"),exchange = @Exchange(value = "routing_exchange"
            ,type = "direct"),
            key = {"error_routing_key"
                    ,
                    "info_routing_key"
                    ,
                    "warning_routing_key"}))
    public void routingConsumerAll(String message) {
        System.out.println("接收到info、error、warning等级别日志消息： "+message);
    }
}