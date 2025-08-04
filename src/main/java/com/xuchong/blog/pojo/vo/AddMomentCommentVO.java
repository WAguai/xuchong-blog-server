package com.xuchong.blog.pojo.vo;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AddMomentCommentVO{
    private Integer id;
    private Integer momentId;
    private Integer userId;
    private String nickName;
    private String content;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
