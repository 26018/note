package com.moon.note.controller;

import com.moon.note.entity.Response;
import com.moon.note.entity.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author MoonLight
 */

@RestController
@RequestMapping("/errorpage")
public class ErrorPageController {

    @RequestMapping("/token-invlid-error")
    public Result<String> error() {
        return new Result<>(Response.TOKEN_IS_INVALID);
    }

}
