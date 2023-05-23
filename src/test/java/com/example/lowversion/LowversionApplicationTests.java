package com.example.lowversion;

import com.example.lowversion.service.SendEmailService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class LowversionApplicationTests {
	@Autowired
	private SendEmailService sendEmailService;
	@Test
	public void sendSimpleMailTest() {
		String to="123151231@qq.com";
		String subject="�����ı��ʼ�������";
		String text="Spring Boot���ı��ʼ��������ݲ���.....";
		sendEmailService.sendSimpleEmail(to,subject,text);
	}
}