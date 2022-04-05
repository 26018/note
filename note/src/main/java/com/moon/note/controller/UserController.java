package com.moon.note.controller;

import com.moon.note.utils.MailUtil;
import com.moon.note.utils.PasswordCheckUtil;
import com.moon.note.utils.StringUtil;
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
    /**
     * randomSalt上一次请求的时间
     */
    long lastSendTime = -1L;
    @Resource
    ExpireTimeConfig expireTimeConfig;
    @Resource
    HashMap<String, UserToken> userTokensMap;
    @Resource
    HashMap<String, Long> verificationCodeMap;

    @PostMapping("/register/randomsalt")
    public Result<String> sendRandomCode(HttpServletRequest request) throws MessagingException, GeneralSecurityException {
        String username = request.getParameter("username");
//        System.out.println(username);
        // TODO 验证邮箱是否已被注册
        if (!StringUtil.stringValidCheck(username)) {
            return new Result<>(Response.PARAMETER_IS_NULL);
        }
        long cu = System.currentTimeMillis();
        if (cu - lastSendTime > expireTimeConfig.getRandomSalt()) {
            lastSendTime = cu;
            String randomSalt = StringUtil.randomSalt(6);
            boolean status = MailUtil.sendEmail(username, randomSalt);
            if (!status) {
                return new Result<>(Response.FAIL);
            }
            verificationCodeMap.put(randomSalt, System.currentTimeMillis());
            return new Result<>(Response.SUCCESS);
        }
        // 返回下一次请求时间间隔
        return new Result<>(Response.FAIL, String.valueOf((expireTimeConfig.getRandomSalt() - System.currentTimeMillis() + lastSendTime) / 1000));
    }

    @PostMapping("/register")
    public Result<String> userRegister(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String randomSalt = request.getParameter("randomsalt");
        // 参数是否为空校验
        if (!StringUtil.stringValidCheck(username) || !StringUtil.stringValidCheck(password)) {
            return new Result<>(Response.PARAMETER_IS_NULL);
        }
        // 邮箱格式验证
        if (!MailUtil.mailValid(username)) {
            return new Result<>(Response.MAIL_IS_VALID);
        }
        // 验证码是否正确及过期校验
        if (!verificationCodeMap.containsKey(randomSalt) ||
                System.currentTimeMillis() - verificationCodeMap.get(randomSalt) > expireTimeConfig.getRandomSalt()) {
            return new Result<>(Response.RANDOMSALT_IS_EXPIRED);
        }
        // 密码强弱验证
        if (!PasswordCheckUtil.evalPassword(password)) {
            return new Result<>(Response.WEAK_PASSWORD);
        }
        return userService.userRegister(username, password);
    }

    @PostMapping("/login")
    public Result<String> userLogin(HttpServletRequest request) throws Exception {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (!StringUtil.stringValidCheck(username) || !StringUtil.stringValidCheck(password)) {
            return new Result<>(Response.PARAMETER_IS_NULL);
        }
        return userService.userLogin(username, password);
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
