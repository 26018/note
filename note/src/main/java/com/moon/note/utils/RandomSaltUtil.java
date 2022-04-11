package com.moon.note.utils;

import java.util.HashMap;

/**
 * @author MoonLight
 */
public class RandomSaltUtil {

    public static HashMap<String, String> randoms;

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
    private static RandomSaltAndExpireTime parse(String str) {
        if (str == null) {
            return null;
        }
        String[] split = str.split(":");
        return new RandomSaltAndExpireTime(split[0], Long.parseLong(split[1]));
    }

    /**
     * 过期是指超过规定时间
     */
    private static Boolean expire(String mail,long expire) {
        if (!randoms.containsKey(mail)) {
            return true;
        }
        RandomSaltAndExpireTime parse = parse(randoms.get(mail));
        return parse != null && System.currentTimeMillis() - parse.lastTime > expire;
    }

    public static boolean userRequestValid(String mail,long expire) {
        return expire(mail, expire);
    }

    public static boolean valid(String mail, String randomSalt) {
        RandomSaltAndExpireTime parse = parse(randoms.get(mail));
        // 验证码错误
        if (parse == null || !randomSalt.equals(parse.randomSalt)) {
            return false;
        }
        randoms.remove(mail);
        return true;
    }

}
