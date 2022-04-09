package com.moon.note.utils;

import com.alibaba.fastjson.JSON;
import com.moon.note.config.ExpireTimeConfig;
import com.moon.note.entity.Response;
import com.moon.note.entity.Result;
import com.moon.note.entity.UserToken;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * @author MoonLight
 */

@Component
public class TokenUtil {

    public static boolean valid(HttpServletRequest request,HashMap<String, UserToken> tokens,long expireTime) {
        String token = request.getHeader("token");
        if (token == null) {
            return false;
        }
        // 生成的token包含+号，发送到前端通过url传到服务端的过程中，+号会被解析为空格
        token = token.replaceAll(" ", "+");
        UserToken userToken = tokens.getOrDefault(token, null);
        // 当token解析错误时抛出异常，表明token无效
        if (userToken == null) {
            try {
                userToken = JSON.parseObject(DesUtil.decrypt(token), UserToken.class);
            } catch (Exception e) {
                throw new IllegalArgumentException();
            }
        }
        // 过期token直接返回
        if (userToken == null || System.currentTimeMillis() - userToken.getExpiredTime() > expireTime) {
            tokens.remove(token);
        }
        return true;
    }
}
