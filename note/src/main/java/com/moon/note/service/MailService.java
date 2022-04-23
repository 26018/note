package com.moon.note.service;

import com.moon.note.utils.MailUtil;
import com.moon.note.utils.RedisUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author MoonLight
 */
@Service
public class MailService {

    @Resource
    RedisUtil redisUtil;

    public void sendMail(String mail, String type, String randomCode) throws Exception {
        boolean sendStatus = MailUtil.send(mail, randomCode);
        if (!sendStatus) {
            throw new Exception("邮件发送失败");
        }
        redisUtil.set(mail, randomCode, Long.parseLong("3600"));
    }

}
