package com.xuchong.blog.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "xuchong-blog.jwt") //表示当前类为配置属性类 将application.yml中的属性sky.jwt 封装成属性对象
@Data
public class JwtProperties {

    /**
     * 管理端生成jwt令牌相关配置
     */
    private String adminSecretKey;
    private long adminTtl;

    /**
     * 用户端生成jwt令牌相关配置
     */
    private String userSecretKey;
    private long userTtl;

    private String tokenName;

}

