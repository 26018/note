package com.moon.note.utils;

import com.alibaba.fastjson.JSON;
import com.moon.note.entity.UserToken;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * @author MoonLight
 */

public class TokenUtil {
    /**
     * 检查token是否合法，合法则储存该token
     */
    public static boolean valid(HttpServletRequest request,HashMap<String, UserToken> tokens,long expireTime) {
        String token = getToken(request);
        UserToken userToken = tokens.getOrDefault(token, null);
        // 当token解析错误时抛出异常，表明token无效
        if (userToken == null && token != null) {
            userToken = getUserToken(token);
        }
        // 过期token检查
        boolean valid = userToken != null && System.currentTimeMillis() - userToken.getExpiredTime() <= expireTime;
        if (valid){
            save(token, tokens);
        }
        return valid;
    }

    /**
     * 存储用户token
     */
    public static void save(String token, HashMap<String, UserToken> map) {
        map.put(token, getUserToken(token));
    }

    /**
     * 根据Token获取用户信息
     */
    public static UserToken getUserToken(String token) {
        UserToken userToken = null;
        try {
            userToken = JSON.parseObject(DesUtil.decrypt(token), UserToken.class);
        } catch (Exception e) {
            // TODO 转到错误controller处理
        }
        return userToken;
    }

    /**
     * 从request中获取Token
     */
    public static String getToken(HttpServletRequest request) {
        String token = request.getHeader("token");
        // 生成的token包含+号，发送到前端通过url传到服务端的过程中，+号会被解析为空格
        if (token != null) {
            token = token.replaceAll(" ", "+");
        }
        return token;
    }

    /**
     * 设置延长token的过期时间
     */
    public static void resetTokenExpireTime(String token, HashMap<String, UserToken> map) {
        UserToken userToken = getUserToken(token);
        userToken.setExpiredTime(System.currentTimeMillis());
        map.put(token, userToken);
    }
}
