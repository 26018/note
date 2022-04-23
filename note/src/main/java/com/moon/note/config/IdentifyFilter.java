package com.moon.note.config;

import com.moon.note.entity.UserToken;
import com.moon.note.utils.TokenUtil;
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

    private static String[] PATHS = {"/users/login", "/users/register", "/mail", "swagger","/errorpage","/redis"};

    @Resource
    ExpireTimeConfig expireTimeConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String requestUrl = request.getRequestURL().toString();
        // 过滤路径判断
        for (String path : PATHS) {
            if (requestUrl.contains(path)) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        }

        // 非controller层的错误不能被全局捕获，所以将其转到controller层
        if (!TokenUtil.valid(request, userTokensMap, expireTimeConfig.getToken())) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/errorpage/token-invlid-error");
            requestDispatcher.forward(request, servletResponse);
        }

        // token时间延期后重新放入列表
        TokenUtil.resetTokenExpireTime(TokenUtil.getToken(request), userTokensMap);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

}
