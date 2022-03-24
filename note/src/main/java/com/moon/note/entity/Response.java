package com.moon.note.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author JinHui
 * @date 2022/1/14
 */

@Getter
@AllArgsConstructor
public enum Response {
    /**
     *
     */
    FAIL(520, "OPERATION FAIL"),
    PARAMETER_IS_NULL(530, "PARAMETER WILL NOT BE NULL"),
    USER_NOT_REGISTER(540, "USER NOT REGISTER"),
    USER_NOT_LOGIN(550, "USER_NOT_LOGIN"),
    USER_ALREADY_EXISTS(560,"USER ALREADY EXISTS"),

    TOKEN_IS_VALID(600,"TOKEN IS VALID"),
    TOKEN_IS_EXPIRED(610,"TOKEN IS EXPIRED"),
    SUCCESS(200, "SUCCESS"),
    ;

    private final int code;
    private final String message;

}
