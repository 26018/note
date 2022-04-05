package com.moon.note.utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.xml.soap.SAAJMetaFactory;
import java.util.Properties;
import java.util.regex.Pattern;

/**
 * @author MoonLight
 */
public class MailUtil {
    //创建一个配置文件并保存
    static Properties properties = new Properties();
    static Session session;
    static String serverMailUserName = "2033471349@qq.com";
    static String serverMailUserPassword = "tbxjsexmhrqidjae";
    static Transport transport;
    static MimeMessage mimeMessage;

    static {
        properties.setProperty("mail.host", "smtp.qq.com");
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.smtp.auth", "true");
        //创建一个session对象
        session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(serverMailUserName, serverMailUserPassword);
            }
        });
        //获取连接对象
        try {
            transport = session.getTransport();
            //连接服务器
            transport.connect("smtp.qq.com", serverMailUserName, serverMailUserPassword);
            //创建邮件对象
            mimeMessage = new MimeMessage(session);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static boolean sendEmail(String userMail, String randomCode) throws MessagingException {
        String content = "Hey " + userMail +
                "<br><br>Your Verification Code:<strong>" + randomCode +
                "</strong><br><br>Thanks,<br>The Note Team";
        try {
//            QQ存在一个特性设置SSL加密
//            MailSSLSocketFactory sf = new MailSSLSocketFactory();
//            sf.setTrustAllHosts(false);
//            properties.put("mail.smtp.ssl.enable", "true");
//            properties.put("mail.smtp.ssl.socketFactory", sf);
            //邮件发送人
            mimeMessage.setFrom(new InternetAddress(serverMailUserName));
            //邮件接收人
            mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(userMail));
            //邮件标题
            mimeMessage.setSubject("[Note] Please verify your device");
            //邮件内容
            mimeMessage.setContent(content, "text/html;charset=UTF-8");
            //发送邮件
            transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
        } catch (Exception e) {
            e.printStackTrace();
            //关闭连接
            transport.close();
            return false;
        }
        return true;
    }

    public static boolean mailValid(String mail) {
        if (mail != null && !mail.isEmpty()) {
            return Pattern.matches("^(\\w+([-.][A-Za-z0-9]+)*){3,18}@\\w+([-.][A-Za-z0-9]+)*\\.\\w+([-.][A-Za-z0-9]+)*$", mail);
        }
        return false;
    }

}
