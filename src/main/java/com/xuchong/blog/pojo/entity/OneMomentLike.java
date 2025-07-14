package com.xuchong.blog.pojo.entity;

import com.xuchong.blog.pojo.entity.db.MomentLike;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class OneMomentLike extends MomentLike {
    private String nickName;
}
