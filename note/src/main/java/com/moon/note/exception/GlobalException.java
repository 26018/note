package com.moon.note.exception;

import com.moon.note.entity.Response;
import com.moon.note.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author MoonLight
 */
@RestControllerAdvice
@ResponseBody
@Slf4j
public class GlobalException {

    @ExceptionHandler(NullPointerException.class)
    public Result<String> exceptionHandler(Exception e) {
        log.error("发生空指针异常:" + e.getMessage());
        return new Result<>(Response.FAIL);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public Result<String> illegalArgumentExceptionHandler(Exception e) {
        log.error("发生非法参数异常:" + e.getMessage());
        return new Result<>(Response.PARAMETER_IS_NULL);
    }

    @ExceptionHandler(value = { BindException.class})
    public Result<String> bind(BindingResult result) {
        StringBuilder stringBuilder = new StringBuilder();
        int len = result.getAllErrors().size();
        for (ObjectError allError : result.getAllErrors()) {
            stringBuilder.append(allError.getDefaultMessage());
            if (--len != 0) {
                stringBuilder.append(",");
            }
        }
        log.error("绑定数据异常:" + result.getAllErrors());
        return new Result<>(Response.FAIL.getCode(), stringBuilder.toString());
    }

    @ExceptionHandler(Exception.class)
    public Result<String> exception(Exception e) {
        log.error("未知异常:" + e.getMessage());
        return new Result<>(Response.FAIL.getCode(), e.getMessage());
    }

}
