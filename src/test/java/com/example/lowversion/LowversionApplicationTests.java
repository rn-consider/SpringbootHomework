package com.example.lowversion;

import com.example.lowversion.entity.Person;
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
	public void psubPublisher() {
		Person person=new Person();
		person.setAge(15);
		person.setName("shitou");
//第一个参数表示定义的交换器名称，第二个表示路由键，第三个表示消息内容
		rabbitTemplate.convertAndSend("fanout_exchange"
				,
				""
				,person);
	}
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
