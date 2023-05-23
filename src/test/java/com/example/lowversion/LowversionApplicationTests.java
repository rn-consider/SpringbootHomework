package com.example.lowversion;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
class LowversionApplicationTests {
	@Autowired
	private AmqpAdmin amqpAdmin;
	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Test
	public void topicPublisher() {
		rabbitTemplate.convertAndSend("topic_exchange"
				,
				"info.email"
				,
				"topics send email message");
		rabbitTemplate.convertAndSend("topic_exchange"
				,
				"info.sms"
				,
				"topics send sms message");
		rabbitTemplate.convertAndSend("topic_exchange"
				,
				"info.email.sms"
				,
				"topics send email and sms message");
	}

	@Test
	public void routingPublisher() {
		rabbitTemplate.convertAndSend("routing_exchange"
				,
				"error_routing_key"
				,
				"routing send info message");
	}
}