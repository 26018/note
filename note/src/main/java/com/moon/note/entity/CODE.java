package com.moon.note.entity;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.annotations.Select;
import org.omg.PortableInterceptor.SUCCESSFUL;

/**
 * @author JinHui
 * @date 2022/1/14
 */

@Getter
public enum CODE {
    // 表示成功
    SUCCESS(200),
    FAIL(99),
    ERROR(202);

    private int code;

    CODE(int c) {
        this.code = c;
    }
}
