package com.xuchong.blog.pojo.entity.db;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("photo")
public class Photo {
    private Integer id;
    private Integer albumId;
    private String url;
    private String name;
    private String description;
    private LocalDate shootTime;
    private String shootLocation;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
