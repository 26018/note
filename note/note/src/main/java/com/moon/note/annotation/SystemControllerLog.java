package com.moon.note.annotation;

import java.lang.annotation.*;

/**
 * @author Moon
 * @date 2022/6/9
 */

@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SystemControllerLog {

}
