package com.moon.note.controller;

import com.moon.note.entity.Response;
import com.moon.note.entity.Result;
import com.moon.note.entity.User;
import com.moon.note.service.MailService;
import com.moon.note.service.UserService;
import com.moon.note.utils.StringUtil;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @author JinHui
 * @date 2022/3/14
 */
@RestController
@RequestMapping("/users")
public class UserController {

//    @Resource
//    RandomSaltUtil randomSaltUtil;
    @Resource
    UserService userService;
    @Resource
    MailService mailService;

    @PostMapping("/register/randomsalt")
    public Result<String> sendRandomCode(@Email(message = "邮箱格式错误") String username, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (userService.exist(username)) {
            return new Result<>(Response.USER_ALREADY_EXISTS);
        }
        mailService.sendMail(username, null, StringUtil.randomSalt(6));
        return new Result<>(Response.SUCCESS);
    }

    @PostMapping("/register")
    public Result<String> register(@Validated User user, @NotBlank(message = "验证码不能为空") @RequestParam String randomsalt) throws Exception {
        userService.register(user, randomsalt);
        return new Result<>(Response.SUCCESS);
    }

    @PostMapping("/login")
    public Result<String> login(@Validated User user) throws Exception {
        String token = userService.login(user);
        return new Result<>(Response.SUCCESS, token);
    }
}
