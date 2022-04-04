package com.moon.note;

import com.moon.note.config.ExpireTimeConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author MoonLight
 */

@SpringBootApplication
@MapperScan("com.moon.note.mapper")
@EnableConfigurationProperties({ExpireTimeConfig.class})
public class NoteApplication {

    public static void main(String[] args) {
        SpringApplication.run(NoteApplication.class, args);
    }
}