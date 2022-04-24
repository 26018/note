package com.moon.note.utils;

import com.alibaba.fastjson.JSON;
import com.moon.note.config.ExpireTimeConfig;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;


/**
 * @author MoonLight
 * Token工具集合
 */

@Component
public class TokenUtil {

    @Resource
    RedisUtil redisUtil;
    @Resource
    ExpireTimeConfig config;

    public void set(String token, String userToken) {
        redisUtil.set(token, userToken, config.getToken());
    }

    public Object get(String token) {
        return redisUtil.get(token);
    }

    public boolean contains(String token) {
        return redisUtil.hasKey(token);
    }

    public <T> T getByType(String token, Class<T> tClass) {
        return JSON.parseObject((String) redisUtil.get(token), tClass);
    }

}
