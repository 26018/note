package com.moon.note.service;

import com.alibaba.fastjson.JSON;
import com.moon.note.utils.DesUtil;
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

    public User getByToken(String token) {
        return userTokensMap.getOrDefault(token, null).getUser();
    }

    public Result<String> userLogin(User user) throws Exception {
        User u = userDao.selectUserByUsername(user.getUsername());
        if (u != null && user.getPassword().equals(u.getPassword())) {
            // 清除密码，避免token中出现用户密码
            user.setPassword("");
            UserToken userToken = new UserToken(user, System.currentTimeMillis());
            String token = DesUtil.encrypt(JSON.toJSONString(userToken));
            userTokensMap.put(token, userToken);
            return new Result<>(Response.SUCCESS, token);
        }
        return new Result<>(Response.USER_NOT_REGISTER);
    }

    public Result<String> userRegister(User user) {
        if (userExist(user.getUsername())) {
            return new Result<>(Response.USER_ALREADY_EXISTS);
        }
        Boolean insertUser = userDao.insertUser(user);
        return insertUser ? new Result<>(Response.SUCCESS) : new Result<>(Response.FAIL);
    }

    public Result<String> userPasswordChange(String id) {
        // 修改密码
        return null;
    }

    public boolean userExist(String username) {
        return userDao.selectUserByUsername(username) != null;
    }
}
