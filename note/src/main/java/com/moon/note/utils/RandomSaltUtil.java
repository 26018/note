package com.moon.note.utils;

import com.moon.note.config.ExpireTimeConfig;
import com.moon.note.entity.Response;
import com.moon.note.entity.Result;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.util.HashMap;

/**
 * @author MoonLight
 */
@Component
public class RandomSaltUtil {

    @Resource
    HashMap<String, String> verificationCodeMap;
    @Resource
    ExpireTimeConfig expireTimeConfig;

    private static class RandomSaltAndExpireTime {
        String randomSalt;
        Long lastTime;

        RandomSaltAndExpireTime(String randomSalt, Long lastTime) {
            this.randomSalt = randomSalt;
            this.lastTime = lastTime;
        }
    }
    /**
     * 解析map里code+time的字符串
     */
    private RandomSaltAndExpireTime parse(String str) {
        if (!StringUtil.valid(str)) {
            return null;
        }
        String[] split = str.split(":");
        return new RandomSaltAndExpireTime(split[0], Long.parseLong(split[1]));
    }

    /**
     * 过期是指超过规定时间
     */
    private Boolean expire(String mail) {
        if (!verificationCodeMap.containsKey(mail)) {
            return true;
        }
        RandomSaltAndExpireTime parse = parse(verificationCodeMap.get(mail));
        return parse != null && System.currentTimeMillis() - parse.lastTime > expireTimeConfig.getRandomSalt();
    }

    public boolean userRequestValid(String mail) {
        return expire(mail);
    }

    public boolean randomSaltValid(String mail, String randomSalt) {
        if (!StringUtil.valid(mail, randomSalt)) {
            return false;
        }
        // 验证码错误
        RandomSaltAndExpireTime parse = parse(verificationCodeMap.get(mail));
        if (parse == null || !randomSalt.equals(parse.randomSalt)) {
            return false;
        }
        verificationCodeMap.remove(mail);
        return true;
    }

}
