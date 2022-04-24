package com.moon.note.controller;

import com.moon.note.entity.Response;
import com.moon.note.entity.Result;
import com.moon.note.entity.User;
import com.moon.note.service.MailService;
import com.moon.note.service.UserService;
import com.moon.note.utils.StringUtil;
import com.moon.note.utils.VerificationUtil;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

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
    MailService mailService;
    @Resource
    VerificationUtil verificationUtil;

    @PostMapping("/register/randomsalt")
    public Result<String> sendRandomCode(@Email(message = "邮箱格式错误")
                                         @NotBlank(message = "邮箱不能为空")
                                                 String username) throws Exception {
        if (userService.exist(username)) {
            return new Result<>(Response.USER_ALREADY_EXISTS);
        }
        if (verificationUtil.exist(username)) {
            return new Result<>(200, "验证码已发送，请勿重复点击");
        }
        String code = StringUtil.randomSalt(6);
        String content = "您好 " + username +
                "<br><br>您的验证码是:<a><strong>" + code +
                "</a></strong><br><br>请尽快输入确认<br>5分钟后失效。<br><br>Note团队";
        mailService.sendMail(username, "[Note] 请确认您的验证码", content);
        verificationUtil.set(username, code);
        return new Result<>(Response.SUCCESS);
    }

    @PostMapping("/register")
    public Result<String> register(@Validated User user,
                                   @NotBlank(message = "验证码不能为空")
                                   @RequestParam String randomsalt) throws Exception {
        userService.register(user, randomsalt);
        return new Result<>(200, "注册成功");
    }

    @PostMapping("/login")
    public Result<String> login(@Validated User user) throws Exception {
        String token = userService.login(user);
        return new Result<>(Response.SUCCESS, token);
    }
}
