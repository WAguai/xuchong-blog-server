package com.xuchong.blog.pojo.entity.db;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user")
public class User implements Serializable {
    private Integer id;
    private String nickName;
    private String email;
    private String password;
    private String isAdmin;
    private Integer visitTimes;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
