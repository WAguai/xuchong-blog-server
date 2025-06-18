package com.xuchong.blog.pojo.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AddGuestCommentVO {
    private Integer id;
    private Integer guestBookId;
    private Integer userId;
    private String nickName;
    private String content;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
