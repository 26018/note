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
    FAIL(520, "请求失败"),
    PARAMETER_IS_NULL(530, "参数不能为空"),
    USER_NOT_REGISTER(540, "用户未注册"),
    USER_NOT_LOGIN(550, "用户未登录"),
    USER_ALREADY_EXISTS(560,"用户已存在"),
    USER_NOT_EXISTS(570,"用户不存在"),
    WEAK_PASSWORD(570,"密码强度较弱"),
    ERROR_PASSWORD(570,"密码错误"),
    MAIL_IS_INVALID(580,"邮箱格式不正确"),

    TOKEN_IS_INVALID(600,"无效的Token"),
    TOKEN_IS_EXPIRED(610,"Token已过期"),

    RANDOMSALT_IS_EXPIRED(620,"验证码已过期"),
    RANDOMSALT_IS_ERROR(630,"验证码错误"),
    REPEAT_REQUEST(640,"重复的请求"),
    SUCCESS(200, "请求成功");

    private final int code;
    private final String message;
}
