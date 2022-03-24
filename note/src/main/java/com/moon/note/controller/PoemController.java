package com.moon.note.controller;

import com.alibaba.fastjson.JSON;
import com.moon.note.entity.Response;
import com.moon.note.entity.Result;
import com.moon.note.mapper.PoemDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@RestController()
@RequestMapping("/poem")
public class PoemController {

    @Resource
    PoemDao poemDao;

    @Value("${poemCount}")
    private int count;
    private int fromIndex = 0;

    @GetMapping("/poems")
    public Result<String> poems() {
        List<HashMap<String, String>> poems = poemDao.poems(count, fromIndex++);
        if (poems.size() < count) {
            fromIndex = 0;
        }
        return new Result<>(Response.SUCCESS, JSON.toJSONString(poems));
    }
}
