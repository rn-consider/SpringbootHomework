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
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


@RunWith(SpringRunner.class)
@SpringBootTest
public class LowversionApplicationTests {
	@Autowired
	private SendEmailService sendEmailService;
	@Test
	public void sendSimpleMailTest() {
		String to="1111111111@qq.com";
		String subject="【纯文本邮件】标题";
		String text="Spring Boot纯文本邮件发送内容测试.....";
		sendEmailService.sendSimpleEmail(to,subject,text);
	}
	@Test
	public void sendComplexEmailTest() {
		String to="1111111111@qq.com";
		String subject="【复杂邮件】标题";
		StringBuilder text = new StringBuilder();
		text.append("<html><head></head>");
		text.append("<body><h1>祝大家元旦快乐！</h1>");
		String rscId = "img001";
		text.append("<img src='cid:" +rscId+"'/></body>");text.append("</html>");
		String rscPath="C:\\newyear.jpg";
		String filePath="C:\\text.txt";
		sendEmailService.sendComplexEmail(to,subject,text.toString(),
				filePath,rscId,rscPath);}
	@Autowired
	private TemplateEngine templateEngine;
	@Test
	public void sendTemplateEmailTest() {
		String to="1111111111@qq.com";
		String subject="【模板邮件】标题";
		Context context = new Context();
		context.setVariable("username", "石头");
		context.setVariable("code", "456123");
		String emailContent = templateEngine.process("emailTemplate_vercode",
				context);
		sendEmailService.sendTemplateEmail(to,subject,emailContent);}


}