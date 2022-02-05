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
    private T message;
}
