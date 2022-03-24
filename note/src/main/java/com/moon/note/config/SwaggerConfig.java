package com.moon.note.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author MoonLight
 */

@Configuration
@EnableSwagger2
@Profile({"dev","test"})
public class SwaggerConfig {

}