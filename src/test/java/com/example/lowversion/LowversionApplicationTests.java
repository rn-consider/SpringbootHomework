package com.example.lowversion;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LowversionApplicationTests {
	@Autowired
	private AmqpAdmin amqpAdmin;
	@Test
	public void amqpAdmin() {
//定义fanout类型的交换器
		amqpAdmin.declareExchange(new FanoutExchange("fanout_exchange"));
//定义两个默认持久化队列分别处理email和sms
		amqpAdmin.declareQueue(new Queue("fanout_queue_email"));
		amqpAdmin.declareQueue(new Queue("fanout_queue_sms"));
//将队列分别与交换器进行绑定
		amqpAdmin.declareBinding(new Binding("fanout_queue_email"
				,
				Binding.DestinationType.QUEUE,
				"fanout_exchange"
				,
				""
				,null));
		amqpAdmin.declareBinding(new Binding("fanout_queue_sms"
				,
				Binding.DestinationType.QUEUE,
				"fanout_exchange"
				,
				""
				,null));
}}
