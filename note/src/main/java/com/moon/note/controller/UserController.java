package com.moon.note.controller;

import com.moon.note.Utils.MailUtil;
import com.moon.note.Utils.StringUtil;
import com.moon.note.config.ExpireTimeConfig;
import com.moon.note.entity.Response;
import com.moon.note.entity.Result;
import com.moon.note.entity.UserToken;
import com.moon.note.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.GeneralSecurityException;
import java.util.HashMap;

/**
 * @author JinHui
 * @date 2022/3/14
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Resource
    UserService userService;

    @Resource
    HashMap<String, UserToken> userTokensMap;

    // 上一次请求的时间
    long lastSendTime = -1L;

    @Resource
    ExpireTimeConfig expireTimeConfig;

    @Resource
    HashMap<String, Long> verificationCodeMap;

    @GetMapping("/register/sendrandomcode")
    public Result<String> sendRandomCode(HttpServletRequest request) throws MessagingException, GeneralSecurityException {
        String username = request.getParameter("username");
        long cu = System.currentTimeMillis();
        if (cu - lastSendTime > expireTimeConfig.getRandomSalt()) {
            lastSendTime = cu;
            String randomSalt = StringUtil.randomSalt(6);
            MailUtil.sendEamil(username, randomSalt);
            verificationCodeMap.put(randomSalt, System.currentTimeMillis());
            return new Result<>(Response.SUCCESS);
        }
        // 返回下一次请求时间间隔
        return new Result<>(Response.FAIL, String.valueOf((expireTimeConfig.getRandomSalt() - System.currentTimeMillis() + lastSendTime) / 1000));
    }
    @PostMapping("/register")
    public Result<String> userRegister(HttpServletRequest request) {
        return userService.userRegister(request.getParameter("username"), request.getParameter("password"),request.getParameter("random-salt"));
    }

    @PostMapping("/login")
    public Result<String> userLogin(HttpServletRequest request) throws Exception {
        return userService.userLogin(request.getParameter("username"), request.getParameter("password"));
    }

    @PostMapping("/logout")
    public Result<String> userLogout(HttpServletRequest request, HttpServletResponse response) {
        // TODO 将token销毁
        String token = request.getParameter("token");
        UserToken userToken = userTokensMap.get(token);
        return null;
    }

    @PostMapping("/password-change")
    public Result<String> userPasswordChange(HttpServletRequest request) {
        // TODO
        return userService.userPasswordChange("");
    }

}
