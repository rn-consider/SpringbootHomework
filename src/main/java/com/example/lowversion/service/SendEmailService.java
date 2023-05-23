package com.example.lowversion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.File;
import java.io.UnsupportedEncodingException;

@Service
public class SendEmailService{
    @Autowired
    private JavaMailSenderImpl mailSender;
    @Value("${spring.mail.username}")
    private String from;
    public void sendSimpleEmail(String to,String subject,String text){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from); message.setTo(to);
        message.setSubject(subject); message.setText(text);
        try { mailSender.send(message);System.out.println("纯文本邮件发送成功");
        } catch (MailException e) {
            System.out.println("纯文本邮件发送失败 "+e.getMessage());e.printStackTrace();}
    }
    public void sendComplexEmail(String to,String subject,String text,String filePath,String rscId,
                                 String rscPath){
        MimeMessage message = mailSender.createMimeMessage();try {
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(from);helper.setTo(to);
        helper.setSubject(subject);helper.setText(text, true);
        FileSystemResource res = new FileSystemResource(new File(rscPath));
        helper.addInline(rscId, res);
        FileSystemResource file = new FileSystemResource(new File(filePath));
        String fileName = filePath.substring(filePath.lastIndexOf(File.separator)+1);
        helper.addAttachment(MimeUtility.encodeText(fileName), file);
        mailSender.send(message);
        System.out.println("复杂邮件发送成功");
    } catch (MessagingException e) {
        System.out.println("复杂邮件发送失败 "+e.getMessage());e.printStackTrace();
    } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
    public void sendTemplateEmail(String to, String subject, String content) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);helper.setTo(to);
            helper.setSubject(subject);helper.setText(content, true);
            mailSender.send(message);System.out.println("模板邮件发送成功");
        } catch (MessagingException e)
        {System.out.println("模板邮件发送失败 "+e.getMessage());
            e.printStackTrace();}}

}
