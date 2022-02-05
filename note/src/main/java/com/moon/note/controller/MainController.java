package com.moon.note.controller;

import com.alibaba.fastjson.JSON;
import com.moon.note.entity.CODE;
import com.moon.note.entity.Result;
import com.moon.note.mapper.PoemDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController()
@RequestMapping("/note")
public class MainController {

    @Autowired
    private PoemDAO poemDAO;

    @GetMapping("/getAll")
    public Result<String> getAllPoem() {
        List<Map<String, String>> allPoem = poemDAO.getAllPoem();
        return new Result<>(CODE.SUCCESS.getCode(), JSON.toJSONString(allPoem));
    }

    @PostMapping("/save/{poemString}")
    public Result<String> savePoem(@PathVariable("poemString") String poemString) {
        try {
            String[] split = poemString.split("<br>");
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 1; i < split.length; i++) {
                stringBuilder.append(split[i]).append('\n');
            }
            int i = poemDAO.savePoem(split[0], stringBuilder.toString());
            if (i == 1) {
                return new Result<>(CODE.SUCCESS.getCode(), "success");
            } else {
                return new Result<>(CODE.FAIL.getCode(), "fail");
            }
        } catch (Exception e) {
            return new Result<>(CODE.ERROR.getCode(), "err");
        }
    }

    @PostMapping("/delete/{id}")
    public Result<String> deletePoem(@PathVariable("id") String id) {
        int i = poemDAO.deletePoem(id);
        return i == 1 ? new Result<>(CODE.SUCCESS.getCode(), "success") : new Result<>(CODE.FAIL.getCode(), "fail");
    }
}
