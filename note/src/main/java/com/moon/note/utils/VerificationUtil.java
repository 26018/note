package com.moon.note.utils;


import com.moon.note.config.ExpireTimeConfig;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 验证码工具集合
 */

@Component
public class VerificationUtil {

    @Resource
    RedisUtil redisUtil;

    @Resource
    ExpireTimeConfig config;


    public void set(String mail, String code) {
        redisUtil.set(mail, code, config.getVerification());
    }

    public Object get(String mail) {
        return redisUtil.get(mail);
    }

}
