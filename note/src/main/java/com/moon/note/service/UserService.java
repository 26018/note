package com.moon.note.service;

import com.alibaba.fastjson.JSON;
import com.moon.note.entity.Response;
import com.moon.note.entity.User;
import com.moon.note.entity.UserToken;
import com.moon.note.mapper.UserDao;
import com.moon.note.utils.DesUtil;
import com.moon.note.utils.PasswordCheckUtil;
import com.moon.note.utils.TokenUtil;
import com.moon.note.utils.VerificationUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author JinHui
 * @date 2022/3/14
 */

@Service
public class UserService {

    @Resource
    UserDao userDao;
    @Resource
    VerificationUtil verificationUtil;
    @Resource
    TokenUtil tokenUtil;

    public String login(User user) throws Exception {
        User u = userDao.selectUserByUsername(user.getUsername());
        if (u == null) {
            throw new Exception(Response.USER_NOT_EXISTS.getMessage());
        }
        if (!user.getPassword().equals(u.getPassword())) {
            throw new Exception(Response.ERROR_PASSWORD.getMessage());
        }
        // 清除密码，避免token中出现用户密码
        u.setPassword("");
        String token = DesUtil.encrypt(JSON.toJSONString(u));
        tokenUtil.set(token, JSON.toJSONString(u));
        return token;
    }

    public void register(User user, String randomSalt) throws Exception {
        if (exist(user.getUsername())) {
            throw new Exception(Response.USER_ALREADY_EXISTS.getMessage());
        }
        // 校验验证码
        if (!randomSalt.equals(verificationUtil.get(user.getUsername()))) {
            throw new Exception(Response.RANDOMSALT_IS_ERROR.getMessage());
        }
        // 密码强度检验
        if (!PasswordCheckUtil.evalPassword(user.getPassword())) {
            throw new Exception(Response.WEAK_PASSWORD.getMessage());
        }
        userDao.insertUser(user);
    }

    public boolean exist(String username) {
        return userDao.selectUserByUsername(username) != null;
    }

    public User getByToken(String token) throws Exception {
        if (!tokenUtil.contains(token)) {
            UserToken userToken = tokenUtil.getByType(token, UserToken.class);
            if (userToken == null) {
                throw new Exception("token解析错误");
            }
            return userToken.getUser();
        }
        return tokenUtil.getByType(token,UserToken.class).getUser();
    }
}
