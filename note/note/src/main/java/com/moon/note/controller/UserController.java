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
    public Result<String> sendRandomCode(
            @Email(message = "邮箱格式错误")
            @NotBlank(message = "邮箱不能为空")
                    String username
    ) throws Exception {

        if (userService.exist(username)) {
            return new Result<>(Response.USER_ALREADY_EXISTS);
        }
        if (verificationUtil.exist(username)) {
            return new Result<>(Response.REPEAT_REQUEST);
        }

        String code = StringUtil.randomSalt(6);
        mailService.sendHtmlMail(username, "[Note] 请确认您的验证码", code);
        verificationUtil.set(username, code);
        return Result.success();
    }

    @PostMapping("/register")
    public Result<String> register(@Validated User user, @NotBlank(message = "验证码不能为空") @RequestParam String randomsalt) throws Exception {
        userService.register(user, randomsalt);
        return Result.success();
    }

    @PostMapping("/login")
    public Result<String> login(@Validated User user) throws Exception {
        return Result.success(userService.login(user));
    }

}
