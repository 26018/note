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
    FAIL(520, "fail"),
    PARAMETER_IS_NULL(530, "parameter will not be null"),
    USER_NOT_REGISTER(540, "user not register"),
    USER_NOT_LOGIN(550, "user not login"),
    USER_ALREADY_EXISTS(560,"user already exist"),
    WEAK_PASSWORD(570,"wake password"),

    TOKEN_IS_VALID(600,"token is valid"),
    TOKEN_IS_EXPIRED(610,"token is expired"),
    SUCCESS(200, "success"),
    ;

    private final int code;
    private final String message;

}
