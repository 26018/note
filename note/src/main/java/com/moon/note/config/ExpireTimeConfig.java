package com.moon.note.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author MoonLight
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "expire", ignoreUnknownFields = true)
public class ExpireTimeConfig {

    /**
     * 用户token过期时间
     */
    long token;

    /**
     * 验证码过期时间
     */
    long verification;

}
