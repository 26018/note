package com.moon.note.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @author MoonLight
 */

@Service
public class MailService {

    @Value("${spring.mail.username}")
    private String from;
    @Resource
    private JavaMailSender mailSender;
    @Resource
    private TemplateEngine templateEngine;

    @Resource
    private Context context;

    @Resource
    private MimeMessage mimeMessage;
    @Resource
    private SimpleMailMessage simpleMailMessage;

    @Resource
    private MimeMessageHelper mimeMessageHelper;

    @Bean
    private TemplateEngine getTemplateEngine() {
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        resolver.setPrefix("/templates/");
        resolver.setSuffix(".html");
        resolver.setTemplateMode("HTML5");
        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(resolver);
        return templateEngine;
    }

    @Bean
    private Context getContext() {
        return new Context();
    }

    @Bean
    private MimeMessage getMiMeMessage() {
        return mailSender.createMimeMessage();
    }

    @Bean
    private SimpleMailMessage getSimpleMailMessage() {
        return new SimpleMailMessage();
    }

    @Bean
    private MimeMessageHelper getMimeMessageHelper() throws MessagingException {
        return new MimeMessageHelper(mimeMessage, true);
    }


    /**
     * 发送简单邮件
     *
     * @param to
     * @param subject
     * @param content
     */
    public void sendSimpleMail(String to, String subject, String content) {
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(content);
        simpleMailMessage.setFrom(from);
        mailSender.send(simpleMailMessage);
    }

    /**
     * 发送HTML邮件
     *
     * @param to
     * @param subject
     * @param content
     * @throws MessagingException
     */
    public void sendHtmlMail(String to, String subject, String content) throws MessagingException {
        mimeMessageHelper.setFrom(from);
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(subject);

        context.clearVariables();
        context.setVariable("username", to);
        context.setVariable("id", content);

        mimeMessageHelper.setText(templateEngine.process("mail", context), true);
        mailSender.send(mimeMessage);
    }
}
