package com.example.lowversion.config;


import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class RabbitMQConfig {
    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }
    //定义fanout类型交换器
    @Bean
    public Exchange fanout_exchange(){
        return ExchangeBuilder.fanoutExchange("fanout_exchange").build();
    }
    //定义两个不同名称的消息队列
    @Bean
    public Queue fanout_queue_email(){
        return new Queue("fanout_queue_email");
    }
    @Bean
    public Queue fanout_queue_sms(){
        return new Queue("fanout_queue_sms");
    }
    //将消息队列与交换器进行绑定
    @Bean
    public Binding bindingEmail(){
        return
                BindingBuilder.bind(fanout_queue_email()).to(fanout_exchange()).with("").noargs();
    }
    @Bean
    public Binding bindingSms(){
        return
                BindingBuilder.bind(fanout_queue_sms()).to(fanout_exchange()).with("").noargs();
    }
}
