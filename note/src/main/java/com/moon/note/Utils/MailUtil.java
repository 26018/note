package com.moon.note.Utils;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.GeneralSecurityException;
import java.util.Properties;

/**
 * @author MoonLight
 */
public class MailUtil {

    public static void sendEamil(String userMail, String randomCode) throws GeneralSecurityException, MessagingException {

        String serverMailUserName = "2033471349@qq.com";
        String serverMailUserPassword = "tbxjsexmhrqidjae";

        String content = "Hey "+userMail+"<br><br>Your Verification Code:" + randomCode + ",<br><br>Thanks,<br>The Note Team";
        //创建一个配置文件并保存
        Properties properties = new Properties();
        properties.setProperty("mail.host", "smtp.qq.com");
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.smtp.auth", "true");

        //QQ存在一个特性设置SSL加密
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.ssl.socketFactory", sf);
        //创建一个session对象
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(serverMailUserName, serverMailUserPassword);
            }
        });
        //开启debug模式
        session.setDebug(false);
        //获取连接对象
        Transport transport = session.getTransport();
        //连接服务器
        transport.connect("smtp.qq.com", serverMailUserName, serverMailUserPassword);
        //创建邮件对象
        MimeMessage mimeMessage = new MimeMessage(session);
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
        //关闭连接
        transport.close();
    }

}
