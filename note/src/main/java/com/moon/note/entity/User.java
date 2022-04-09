package com.moon.note.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author JinHui
 * @date 2022/3/14
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class User {

    long id;
    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    String username;
    @NotBlank(message = "密码不能为空")
    @Size(min = 6, max = 20,message = "密码长度为6-20的字母数字组合")
    String password;
    String salt;
}
