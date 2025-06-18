package com.xuchong.blog.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OneGuestComment {
    private Integer id;
    private Integer guestBookId;
    private Integer userId;
    private String nickName;
    private String content;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
