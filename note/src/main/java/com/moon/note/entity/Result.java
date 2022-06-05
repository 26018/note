package com.moon.note.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author JinHui
 * @date 2022/1/14
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public
class Result<T> {
    private int code;
    private String message;
    private T data;

    public Result(Response r) {
        this.code = r.getCode();
        this.message = r.getMessage();
    }
    public Result(Response r, T data) {
        this.code = r.getCode();
        this.message = r.getMessage();
        this.data = data;
    }

    public Result(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static Result<String> success(){
        return new Result(Response.SUCCESS);
    }
    public static Result<String> success(Object data){
        return new Result(Response.SUCCESS, data);
    }
}
