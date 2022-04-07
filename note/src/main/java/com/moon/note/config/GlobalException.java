package com.moon.note.config;

import com.moon.note.entity.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import javax.servlet.http.HttpServletRequest;

/**
 * @author MoonLight
 */
@ControllerAdvice
public class GlobalException {

    @ExceptionHandler
    public Result<String> exceptionHandler(HttpServletRequest request, Exception e) {

        return null;
    }

}
