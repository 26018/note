package com.moon.note.controller;

import com.moon.note.entity.Result;
import com.moon.note.entity.UserToken;
import com.moon.note.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
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
    UserService userService;

    @Resource
    HashMap<String, UserToken> userTokensMap;

    @PostMapping("/register")
    public Result<String> userRegister(HttpServletRequest request) {
        return userService.userRegister(request.getParameter("username"), request.getParameter("password"));
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
