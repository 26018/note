package com.moon.note.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.moon.note.entity.Result;
import com.moon.note.mapper.PoemDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;

/**
 * @author MoonLight
 */
@RestController()
@RequestMapping("/poem")
public class PoemController {

    @Resource
    PoemDao poemDao;

    @Value("${poemCount}")
    private int count;

    private final JSONArray json = new JSONArray();

    @PostMapping("/poems")
    public Result<String> poems(@NotNull(message = "index为空") @RequestParam String index) {
        int idx = Integer.parseInt(index);
        List<HashMap<String, String>> poems = poemDao.poems(count, idx * count);
        if (poems.size() < count) {
            poems = poemDao.poems(count, Math.max(0, idx - 1) * count);
            idx = -1;
        }
        json.clear();
        json.add(JSON.parse("{index:" + (idx + 1) + "}"));
        json.add(poems);
        return Result.success(json.toJSONString());
    }

}
