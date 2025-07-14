package com.xuchong.blog.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "xuchong-blog.jwt") //表示当前类为配置属性类 将application.yml中的属性sky.jwt 封装成属性对象
@Data
public class JwtProperties {

    private String secretKey;
    private long ttl;
    private String tokenName;

}

