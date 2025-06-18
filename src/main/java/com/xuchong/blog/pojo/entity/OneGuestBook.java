package com.xuchong.blog.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OneGuestBook {
    private Integer id;
    private Integer userId;
    private String nickName;
    private String message;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private List<OneGuestComment> guestComments;
}
