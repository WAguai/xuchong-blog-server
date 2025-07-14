package com.xuchong.blog.pojo.entity.db;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("moment_comment")
public class MomentComment {
    private Integer id;
    private Integer momentId;
    private Integer userId;
    private String content;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
