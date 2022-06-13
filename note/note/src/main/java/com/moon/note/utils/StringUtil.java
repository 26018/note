package com.moon.note.utils;

import java.security.SecureRandom;
import java.util.Random;

/**
 * @author JinHui
 * @date 2022/3/15
 */

public class StringUtil {

    static String base = "0123456789";

    static Random random = new SecureRandom();

    public static String randomSalt(int len) {
        StringBuilder stringBuilder = new StringBuilder();
        int length = base.length();
        for (int i = 0; i < len; i++) {
            stringBuilder.append(base.charAt(random.nextInt(length)));
        }
        return stringBuilder.toString();
    }

}
