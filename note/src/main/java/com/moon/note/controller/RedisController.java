package com.moon.note.controller;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author MoonLight
 */
@RestController()
@RequestMapping("/redis")
public class RedisController {

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @GetMapping("index")
    public String index() {
        ValueOperations<String, String> stringStringValueOperations = stringRedisTemplate.opsForValue();
        stringStringValueOperations.set("jay", "jh");
        return "success";
    }

    @GetMapping("index/{key}")
    public String get(@PathVariable() String key) {
        ValueOperations<String, String> stringStringValueOperations = stringRedisTemplate.opsForValue();
        String s = stringStringValueOperations.get(key);
        return s;
    }

}
