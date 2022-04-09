package com.moon.note.config;

import com.moon.note.entity.Response;
import com.moon.note.entity.Result;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author MoonLight
 */
@ControllerAdvice
@ResponseBody
public class GlobalException {

    @ExceptionHandler(NullPointerException.class)
    public Result<String> exceptionHandler(HttpServletRequest request, Exception e) {
        System.out.println("here...");
        e.printStackTrace();
        return null;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public Result<String> illegalArgumentExceptionHandler(HttpServletRequest request, Exception e) {
        System.out.println("not here...");
        e.printStackTrace();
        return null;
    }

    @ExceptionHandler()
    public Result<String> g(BindingResult result) {
        System.out.println("come here...");
        return new Result<>(Response.FAIL.getCode(),result.getFieldError().getDefaultMessage());
    }

}
