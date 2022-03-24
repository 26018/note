package com.moon.note.config;

import com.moon.note.entity.UserToken;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * @author JinHui
 * @date 2022/3/14
 */

@Configuration
public class BeanConfig {
    /**
     * 将token与用户关联，方便取出获取的数据
      */
    @Bean
    HashMap<String, UserToken> userTokensMap(){
        return new HashMap<>(16);
    }

}
