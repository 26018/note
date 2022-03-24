package com.moon.note.service;

import com.alibaba.fastjson.JSON;
import com.moon.note.Util.DesUtil;
import com.moon.note.Util.StringUtil;
import com.moon.note.entity.*;
import com.moon.note.mapper.UserDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @author JinHui
 * @date 2022/3/14
 */

@Service
public class UserService {

    @Resource
    UserDao userDao;

    @Resource
    HashMap<String, UserToken> userTokensMap;

    public Result<String> userLogin(String username, String password) throws Exception {
        if (!StringUtil.stringValidCheck(username) || !StringUtil.stringValidCheck(password)) {
            return new Result<>(Response.PARAMETER_IS_NULL);
        }
        User user = userDao.selectUserByUsername(username);
        if (user != null && password.equals(user.getPassword())) {
            // 清除密码，避免token中出现用户密码
            user.setPassword("");
            UserToken userToken = new UserToken(user, System.currentTimeMillis());
            String s = JSON.toJSONString(userToken);
            String token = DesUtil.encrypt(s);
            userTokensMap.put(token, userToken);
            return new Result<>(Response.SUCCESS, token);
        }
        return new Result<>(Response.USER_NOT_REGISTER);
    }

    public Result<String> userRegister(String username, String password) {
        if (!StringUtil.stringValidCheck(username) || !StringUtil.stringValidCheck(password)) {
            return new Result<>(Response.PARAMETER_IS_NULL);
        }
        User user = userDao.selectUserByUsername(username);
        if (user != null) {
            return new Result<>(Response.USER_ALREADY_EXISTS);
        }
        Boolean insertUser = userDao.insertUser(username, password);
        if (!insertUser) {
            return new Result<>(Response.FAIL);
        }
        return new Result<>(Response.SUCCESS);
    }

    public Result<String> userPasswordChange(String id) {

        return null;
    }
}
