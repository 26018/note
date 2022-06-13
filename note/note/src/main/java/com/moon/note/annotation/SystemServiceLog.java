package com.moon.note.annotation;

import java.lang.annotation.*;

/**
 * @author: Moon
 * @date: 2022/6/9
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
public @interface SystemServiceLog {

}
