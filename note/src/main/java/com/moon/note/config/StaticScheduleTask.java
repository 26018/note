package com.moon.note.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author MoonLight
 */
//1.主要用于标记配置类，兼备Component的效果。
@Configuration
// 2.开启定时任务
@EnableScheduling
public class StaticScheduleTask {

    @Resource
    HashMap<String, String> verificationCodeMap;
    @Resource
    ExpireTimeConfig expireTimeConfig;
    static Stack<String> stack = new Stack<>();

    // 每天凌晨1点执行

    @Scheduled(cron = "0 0 1 * * ?")
    private void configureTasks() {
        stack.clear();
        for (Map.Entry<String, String> stringStringEntry : verificationCodeMap.entrySet()) {
            if (System.currentTimeMillis() - Long.parseLong(stringStringEntry.getValue().split(":")[1]) > expireTimeConfig.getRandomSalt()) {
                stack.add(stringStringEntry.getKey());
            }
        }
        while (!stack.isEmpty()) {
            verificationCodeMap.remove(stack.pop());
        }
    }
}