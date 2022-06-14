package com.moon.note;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author Moon
 */

@SpringBootApplication
@MapperScan("com.moon.note.mapper")
public class NoteApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(NoteApplication.class, args);
    }
}