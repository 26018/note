package com.moon.note.config;

import com.alibaba.fastjson.JSON;
import com.moon.note.utils.DesUtil;
import com.moon.note.entity.Response;
import com.moon.note.entity.Result;
import com.moon.note.entity.UserToken;
import com.moon.note.utils.StringUtil;
import com.moon.note.utils.TokenUtil;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @author JinHui
 * @date 2022/3/7
 */

@Configuration
public class IdentifyFilter implements Filter {

    private static final HashSet<String> PATHS;

    static {
        PATHS = new HashSet<>();
        PATHS.addAll(Arrays.asList("/users/login", "/users/register", "swagger"));
    }

    @Resource
    HashMap<String, UserToken> userTokensMap;

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
        String token = request.getHeader("token");
        if (!TokenUtil.valid(request, userTokensMap, expireTimeConfig.getToken())) {
            // todo 转到Controller层处理
        }
        // token时间延期后重新放入列表
//        userToken.setExpiredTime(System.currentTimeMillis());
//        userTokensMap.put(token, userToken);
//        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

}
