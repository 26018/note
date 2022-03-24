package com.moon.note.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author JinHui
 * @date 2022/3/14
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserToken {
    User user;
    long expiredTime;
}
