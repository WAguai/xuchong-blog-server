package com.xuchong.blog.pojo.dto;

import lombok.Data;

@Data
public class AddGuestBookMessageDTO {
    private Integer userId;
    private String nickName;
    private String message;
}
