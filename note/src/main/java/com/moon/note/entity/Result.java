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

}