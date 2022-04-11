package com.moon.note.service;

import com.alibaba.fastjson.JSON;
import com.moon.note.utils.DesUtil;
import com.moon.note.entity.*;
import com.moon.note.mapper.UserDao;
import com.moon.note.utils.PasswordCheckUtil;
import com.moon.note.utils.RandomSaltUtil;
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
    @Resource
    HashMap<String, String> verificationCodeMap;

    public String login(User user) throws Exception {
        User u = userDao.selectUserByUsername(user.getUsername());
        if (u == null) {
            throw new Exception(Response.USER_NOT_EXISTS.getMessage());
        }
        if (!user.getPassword().equals(u.getPassword())) {
            throw new Exception(Response.ERROR_PASSWORD.getMessage());
        }
        // 清除密码，避免token中出现用户密码
        user.setPassword("");
        UserToken userToken = new UserToken(user, System.currentTimeMillis());
        String token = DesUtil.encrypt(JSON.toJSONString(userToken));
        userTokensMap.put(token, userToken);
        return token;
    }

    public void register(User user, String randomSalt) throws Exception {
        if (exist(user.getUsername())) {
            throw new Exception(Response.USER_ALREADY_EXISTS.getMessage());
        }
        // 设置工具类的集合 再使用工具类
        RandomSaltUtil.randoms = verificationCodeMap;
        if (!RandomSaltUtil.valid(user.getUsername(), randomSalt)) {
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

    public User getByToken(String token){
        return userTokensMap.get(token).getUser();
    }


}
