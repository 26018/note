package com.moon.note.service;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @author MoonLight
 */
@Service
public class MailService {

    private final static String SERVER_MAIL_USER_NAME = "2033471349@qq.com";
    private final static String SERVER_MAIL_USER_PASSWORD = "tbxjsexmhrqidjae";
    private final static String HOST = "smtp.qq.com";
    private final static Session SESSION;
    private final static MimeMessage MIME_MESSAGE;
    private final static Properties PROPERTIES = new Properties();
    private Transport transport;

    static {
        PROPERTIES.setProperty("mail.host", HOST);
        PROPERTIES.setProperty("mail.transport.protocol", "smtp");
        PROPERTIES.setProperty("mail.smtp.auth", "true");
        SESSION = Session.getDefaultInstance(PROPERTIES, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SERVER_MAIL_USER_NAME, SERVER_MAIL_USER_PASSWORD);
            }
        });
        MIME_MESSAGE = new MimeMessage(SESSION);
        try {
            MIME_MESSAGE.setFrom(new InternetAddress(SERVER_MAIL_USER_NAME));
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private Transport getTransport() throws MessagingException {
        Transport transport = SESSION.getTransport();
        transport.connect(HOST, SERVER_MAIL_USER_NAME, SERVER_MAIL_USER_PASSWORD);
        return transport;
    }

    private MimeMessage getMailObject(String userMail, String mailTitle, String content) throws MessagingException {
        //邮件接收人
        MIME_MESSAGE.setRecipient(Message.RecipientType.TO, new InternetAddress(userMail));
        //邮件标题
        MIME_MESSAGE.setSubject(mailTitle);
        //邮件内容
        MIME_MESSAGE.setContent(content, "text/html;charset=UTF-8");
        return MIME_MESSAGE;
    }

    @PostConstruct
    void init() throws MessagingException {
        transport = getTransport();
    }

    public void sendMail(String userMail, String mailTitle, String content) throws MessagingException {
        MimeMessage mailObject = getMailObject(userMail, mailTitle, content);
        transport.sendMessage(mailObject, mailObject.getAllRecipients());
    }
}
