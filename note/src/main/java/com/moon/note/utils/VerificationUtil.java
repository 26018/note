package com.moon.note.utils;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 验证码工具集合
 * @author MoonLight
 */

@Component
public class VerificationUtil {

    @Resource
    private RedisUtil redisUtil;

    @Value("${expire.verification}")
    public int expiredTime;

    public void set(String mail, String code) {
        if (exist(mail)) {
            return;
        }
        redisUtil.set(mail, code, expiredTime);
    }

    public Object get(String mail) {
        return redisUtil.get(mail);
    }

    public boolean exist(String mail) {
        return redisUtil.hasKey(mail);
    }

}
