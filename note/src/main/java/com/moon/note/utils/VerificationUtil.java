package com.moon.note.utils;


import com.moon.note.config.ExpireTimeConfig;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 验证码工具集合
 * @author MoonLight
 */

@Component
public class VerificationUtil {

    @Resource
    RedisUtil redisUtil;

    @Resource
    ExpireTimeConfig config;

    public void set(String mail, String code) {
        if (exist(mail)) {
            return;
        }
        redisUtil.set(mail, code, config.getVerification());
    }

    public Object get(String mail) {
        return redisUtil.get(mail);
    }

    public boolean exist(String mail) {
        return redisUtil.hasKey(mail);
    }

}
