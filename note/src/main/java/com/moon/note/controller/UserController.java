package com.moon.note.controller;

import com.moon.note.utils.MailUtil;
import com.moon.note.utils.PasswordCheckUtil;
import com.moon.note.utils.RandomSaltUtil;
import com.moon.note.utils.StringUtil;
import com.moon.note.entity.Response;
import com.moon.note.entity.Result;
import com.moon.note.entity.UserToken;
import com.moon.note.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * @author JinHui
 * @date 2022/3/14
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Resource
    MailUtil mailUtil;
    @Resource
    RandomSaltUtil randomSaltUtil;
    @Resource
    UserService userService;
    @Resource
    HashMap<String, UserToken> userTokensMap;

    @PostMapping("/register/randomsalt")
    public Result<String> sendRandomCode(HttpServletRequest request) throws MessagingException {
        String username = request.getParameter("username");
        if (!StringUtil.valid(username)) {
            return new Result<>(Response.PARAMETER_IS_NULL);
        }
        if (!mailUtil.mailValid(username)) {
            return new Result<>(Response.MAIL_IS_INVALID);
        }
        // 该邮箱请求在过期时间内
        if (!randomSaltUtil.userRequestValid(username)) {
            return new Result<>(Response.REPEAT_REQUEST);
        }
        // 邮箱是否已被注册验证
        if (!userService.userValidCheck(username)) {
            return new Result<>(Response.USER_ALREADY_EXISTS);
        }
        // 发送验证码
        if (mailUtil.sendRandomSaltToUser(username)) {
            return new Result<>(Response.SUCCESS);
        }
        return new Result<>(Response.FAIL);
    }

    @PostMapping("/register")
    public Result<String> userRegister(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String randomSalt = request.getParameter("randomsalt");

        // 参数是否为空校验
        if (!StringUtil.valid(username, password)) {
            return new Result<>(Response.PARAMETER_IS_NULL);
        }
        // 邮箱格式验证
        if (!mailUtil.mailValid(username)) {
            return new Result<>(Response.MAIL_IS_INVALID);
        }
        // 验证码错误
        if (!randomSaltUtil.randomSaltValid(username, randomSalt)) {
            return new Result<>(Response.RANDOMSALT_IS_ERROR);
        }
        // 密码较弱
        if (!PasswordCheckUtil.evalPassword(password)) {
            return new Result<>(Response.WEAK_PASSWORD);
        }
        return userService.userRegister(username, password);
    }

    @PostMapping("/login")
    public Result<String> userLogin(HttpServletRequest request) throws Exception {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (!StringUtil.valid(username, password)) {
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
