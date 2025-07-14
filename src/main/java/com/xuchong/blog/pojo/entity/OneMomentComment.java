package com.xuchong.blog.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OneMomentComment {
    private Integer id;
    private Integer momentId;
    private Integer userId;
    private String nickName;
    private String content;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
