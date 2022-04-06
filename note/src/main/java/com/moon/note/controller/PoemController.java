package com.moon.note.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.moon.note.entity.Response;
import com.moon.note.entity.Result;
import com.moon.note.mapper.PoemDao;
import net.sf.jsqlparser.statement.create.table.Index;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.spring.web.json.Json;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@RestController()
@RequestMapping("/poem")
public class PoemController {

    @Resource
    PoemDao poemDao;

    @Value("${poemCount}")
    private int count;

    JSONArray json = new JSONArray();

    @PostMapping("/poems")
    public Result<String> poems(HttpServletRequest request) {
        String indexStr = request.getParameter("index");
        int index = indexStr == null ? 0 : Integer.parseInt(indexStr);
        List<HashMap<String, String>> poems = poemDao.poems(count, index * count);
        if (poems.size() < count) {
            poems = poemDao.poems(count, Math.max(0, index - 1) * count);
            index = -1;
        }
        json.clear();
        json.add(JSON.parse("{index:" + (index + 1) + "}"));
        json.add(poems);
        return new Result<>(Response.SUCCESS, json.toJSONString());
    }

}
