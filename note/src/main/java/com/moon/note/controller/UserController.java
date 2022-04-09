package com.moon.note.controller;

import com.moon.note.entity.User;
import com.moon.note.utils.MailUtil;
import com.moon.note.utils.PasswordCheckUtil;
import com.moon.note.utils.RandomSaltUtil;
import com.moon.note.utils.StringUtil;
import com.moon.note.entity.Response;
import com.moon.note.entity.Result;
import com.moon.note.entity.UserToken;
import com.moon.note.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;
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

    @PostMapping("/register/randomsalt")
    public Result<String> sendRandomCode(User user) throws MessagingException {
        // 该邮箱请求在过期时间内
        if (!randomSaltUtil.userRequestValid(user.getUsername())) {
            return new Result<>(Response.REPEAT_REQUEST);
        }
        // 邮箱是否已被注册验证
        if (userService.userExist(user.getUsername())) {
            return new Result<>(Response.USER_ALREADY_EXISTS);
        }
        // 发送验证码
        return mailUtil.sendRandomSaltToUser(user.getUsername()) ? new Result<>(Response.SUCCESS) : new Result<>(Response.FAIL);
    }

    @PostMapping("/register")
    public Result<String> userRegister(User user, @RequestParam String randomsalt) {
        // 验证码错误
        if (!randomSaltUtil.randomSaltValid(user.getUsername(), randomsalt)) {
            return new Result<>(Response.RANDOMSALT_IS_ERROR);
        }
        // 密码较弱
        if (!PasswordCheckUtil.evalPassword(user.getPassword())) {
            return new Result<>(Response.WEAK_PASSWORD);
        }
        return userService.userRegister(user);
    }

    @PostMapping("/login")
    public Result<String> userLogin(@Valid User user) throws Exception {
        return userService.userLogin(user);
    }






    @PostMapping("/logout")
    public Result<String> userLogout(HttpServletRequest request, HttpServletResponse response) {
        // TODO 将token销毁

        return null;
    }

    @PostMapping("/password-change")
    public Result<String> userPasswordChange(HttpServletRequest request) {
        // TODO
        return userService.userPasswordChange("");
    }

}
