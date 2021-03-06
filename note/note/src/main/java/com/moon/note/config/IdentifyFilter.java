package com.moon.note.config;

import com.moon.note.utils.TokenUtil;
import lombok.extern.slf4j.Slf4j;
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
        boolean invalid = true;
        try {
            if (tokenUtil.valid(request.getHeader("token"))) {
                invalid = false;
                filterChain.doFilter(servletRequest, servletResponse);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (invalid) {
            request.getRequestDispatcher("/errorPage/token-invalid-error").forward(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    @Override
    public String toString() {
        return "IdentifyFilter{" +
                "nocheck=" + Arrays.toString(nocheck) +
                ", tokenUtil=" + tokenUtil +
                '}';
    }
}
