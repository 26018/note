package com.moon.note.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author JinHui
 * @date 2022/3/14
 */

@Data
@AllArgsConstructor
@NoArgsConstructor

public class User {
    long id;
    String username;
    String password;
    String salt;
}
