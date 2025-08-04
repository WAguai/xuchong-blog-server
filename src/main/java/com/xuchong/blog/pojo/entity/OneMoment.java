package com.xuchong.blog.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OneMoment {
    private Integer id;
    private Integer userId;
    private String nickName;
    private String title;
    private String content;
    private List<String> images;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private List<OneMomentLike> likes;
    private List<OneMomentComment> comments;
    private Boolean deleted;
}
