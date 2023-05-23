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
    @RabbitListener(bindings =@QueueBinding(value = @Queue("topic_queue_email"),exchange = @Exchange(value = "topic_exchange"
            ,type = "topic"),
            key = "info.#.email.#"))
    public void topicConsumerEmail(String message) {
        System.out.println("接收到邮件订阅需求处理消息： "+message);
    }
    @RabbitListener(bindings =@QueueBinding(value = @Queue("topic_queue_sms"),exchange = @Exchange(value = "topic_exchange"
            ,type = "topic"),
            key = "info.#.sms.#"))
    public void topicConsumerSms(String message) {
        System.out.println("接收到短信订阅需求处理消息： "+message);
    }
}