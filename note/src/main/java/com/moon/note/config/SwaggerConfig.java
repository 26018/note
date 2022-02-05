package com.moon.note.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration //配置类
@EnableSwagger2// 开启Swagger2的自动配置
@Profile({"dev","test"})
public class SwaggerConfig {

}