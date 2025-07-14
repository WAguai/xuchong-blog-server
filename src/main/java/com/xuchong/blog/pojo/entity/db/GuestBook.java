package com.xuchong.blog.pojo.entity.db;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("guest_book")
public class GuestBook {
    private Integer id;
    private Integer userId;
    private String message;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
