package com.moon.note.service;

import com.moon.note.utils.MailUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @author MoonLight
 */
@Service
public class MailService {

    @Resource
    HashMap<String, String> verificationCodeMap;

    public void sendMail(String mail, String type, String content) throws Exception {
        boolean sendStatus = MailUtil.send(mail, content);
        if (!sendStatus) {
            throw new Exception("邮件发送失败");
        }
        verificationCodeMap.put(mail, content + ":" + System.currentTimeMillis());
    }

}
