package com.xuchong.blog.pojo.dto;

import lombok.Data;

@Data
public class AddGuestCommentDTO {
    private Integer guestBookId;
    private Integer userId;
    private String nickName;
    private String content;
}
