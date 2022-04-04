package com.moon.note.Utils;

import java.util.Random;

/**
 * @author JinHui
 * @date 2022/3/15
 */

public class StringUtil {

     static String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZ-abcdefghijklmnopqrstuvwxyz_0123456789=+";

    public static boolean stringValidCheck(String str) {
        return str != null && str.trim().length() != 0;
    }

    public static String randomSalt(int len) {
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        int length = base.length();
        for (int i = 0; i < len; i++) {
            stringBuilder.append(base.charAt(random.nextInt(length)));
        }
        return stringBuilder.toString();
    }

}
