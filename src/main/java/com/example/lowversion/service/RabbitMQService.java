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
    @RabbitListener(bindings =@QueueBinding(value = @Queue("fanout_queue_email"), exchange = @Exchange(value = "fanout_exchange"
            ,type = "fanout")))
    public void psubConsumerEmailAno(Person user) {
        System.out.println("邮件业务接收到消息： "+user);
    }


    @RabbitListener(bindings =@QueueBinding(value = @Queue("fanout_queue_sms"),exchange = @Exchange(value = "fanout_exchange"
            ,type = "fanout")))
    public void psubConsumerSmsAno(Person user) {
        System.out.println("短信业务接收到消息： "+user);
    }

}