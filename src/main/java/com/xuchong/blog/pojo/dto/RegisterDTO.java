package com.xuchong.blog.pojo.dto;

import lombok.Data;

@Data
public class RegisterDTO {
    private String email;
    private String password;
    private String nickName;
    private String code;
}
