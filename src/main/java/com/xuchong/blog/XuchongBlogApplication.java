package com.xuchong.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.xuchong.blog.server.mapper")
public class XuchongBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(XuchongBlogApplication.class, args);
    }

}
