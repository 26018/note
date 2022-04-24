package com.moon.note.config;

import com.alibaba.fastjson.JSON;
import com.moon.note.entity.UserToken;
import com.moon.note.utils.DesUtil;
import com.moon.note.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author JinHui
 * @date 2022/3/7
 */

@Configuration
public class IdentifyFilter implements Filter {

    @Value("${paths.nocheck}")
    private  String[] nocheck;

    @Resource
    ExpireTimeConfig expireTimeConfig;
    @Resource
    TokenUtil tokenUtil;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String requestUrl = request.getRequestURL().toString();
        // 过滤路径判断
        System.out.println(Arrays.toString(nocheck));
        for (String path : nocheck) {
            if (requestUrl.contains(path)) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        }
        String token = request.getHeader("token");
        UserToken userToken = null;
        if (!tokenUtil.contains(token)) {
            try {
                userToken = JSON.parseObject(DesUtil.decrypt(token), UserToken.class);
                boolean valid = userToken != null && System.currentTimeMillis() - userToken.getExpiredTime() <= expireTimeConfig.getToken();
                if (!valid) {
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("/errorpage/token-invlid-error");
                    requestDispatcher.forward(request, servletResponse);
                }
            } catch (Exception e) {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/errorpage/token-invlid-error");
                requestDispatcher.forward(request, servletResponse);
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

}
