package com.moon.note.config;

import com.moon.note.utils.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author JinHui
 * @date 2022/3/7
 */

@Configuration
@Slf4j
public class IdentifyFilter implements Filter {


    @Value("${paths.nocheck}")
    private String[] nocheck;

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
        for (String path : nocheck) {
            if (requestUrl.contains(path)) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        }
        // token 验证
        boolean invalid = false;
        try {
            if (tokenUtil.valid(request.getHeader("token"))) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                invalid = true;
            }
        } catch (Exception e) {
        }
        if (invalid) {
            request.getRequestDispatcher("/errorpage/token-invlid-error").forward(request, servletResponse);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

}
