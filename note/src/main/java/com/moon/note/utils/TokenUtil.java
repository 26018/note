package com.moon.note.utils;

import com.alibaba.fastjson.JSON;
import com.moon.note.entity.UserToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


/**
 * @author MoonLight
 * Token工具集合
 */

@Component
@Slf4j
public class TokenUtil {

    @Resource
    RedisUtil redisUtil;

    @Value("${expire.token}")
    public int expiredTime;

    public void set(String token, String userToken) {
        redisUtil.set(token, userToken, expiredTime);
    }

    public Object get(String token) {
        return redisUtil.get(token);
    }

    public boolean contains(String token) {
        return redisUtil.hasKey(token);
    }

    public <T> T getByType(String token, Class<T> tClass) {
        return JSON.parseObject((String) get(token), tClass);
    }

    public boolean valid(String token) {
        UserToken userToken;
        if (token == null) {
            return false;
        }
        if (!contains(token)) {
            try {
                userToken = JSON.parseObject(DesUtil.decrypt(token), UserToken.class);
                return (userToken != null) && (System.currentTimeMillis() - userToken.getExpiredTime() <= expiredTime);
            } catch (Exception e) {
                log.error("token解析异常:" + token);
                return false;
            }
        }
        return true;
    }
}
