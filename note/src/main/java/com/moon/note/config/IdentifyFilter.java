package com.moon.note.config;

import com.alibaba.fastjson.JSON;
import com.moon.note.Util.DesUtil;
import com.moon.note.entity.Response;
import com.moon.note.entity.Result;
import com.moon.note.entity.UserToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author JinHui
 * @date 2022/3/7
 */

@Configuration
public class IdentifyFilter implements Filter {

    @Resource
    HashMap<String, UserToken> userTokensMap;

    @Value("${token-expire}")
    long expireTime;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String requestUrl = request.getRequestURL().toString();

        // 登录，注册 放行
        if (requestUrl.contains("/users/login") || requestUrl.contains("/users/register") || requestUrl.contains("swagger")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        ServletOutputStream writer = servletResponse.getOutputStream();
        String token = request.getHeader("token");
        // 未携带token直接返回
        if (token == null) {
            writer.write(JSON.toJSONString(new Result<>(Response.USER_NOT_LOGIN)).getBytes());
            writer.close();
            return;
        }

        // 生成的token包含+号，发送到前端通过url传到服务端的过程中，+号会被解析为空格
        token = token.replaceAll(" ", "+");
        UserToken userToken;
        if (userTokensMap.containsKey(token)) {
            userToken = userTokensMap.get(token);
        } else {
            // 当token解析错误时抛出异常，表明token无效
            try {
                userToken = JSON.parseObject(DesUtil.decrypt(token), UserToken.class);
            } catch (Exception e) {
                writer.write(JSON.toJSONString(new Result<>(Response.TOKEN_IS_VALID)).getBytes());
                writer.close();
                return;
            }
        }

        // 过期token直接返回
        if (userToken == null || System.currentTimeMillis() - userToken.getExpiredTime() > expireTime) {
            userTokensMap.remove(token);
            writer.write(JSON.toJSONString(new Result<>(Response.TOKEN_IS_EXPIRED)).getBytes());
            writer.close();
            return;
        }

        // token时间延期后重新放入列表
        userToken.setExpiredTime(System.currentTimeMillis());
        userTokensMap.put(token, userToken);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

}
