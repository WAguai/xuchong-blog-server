package com.xuchong.blog.pojo.entity.db;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("guest_comment")
public class GuestComment {
    private Integer id;
    private Integer guestBookId;
    private Integer userId;
    private String content;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
