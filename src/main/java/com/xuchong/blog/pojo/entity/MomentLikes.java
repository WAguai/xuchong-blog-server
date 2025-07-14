package com.xuchong.blog.pojo.entity;

import lombok.Data;

import java.util.List;

@Data
public class MomentLikes {
    private Integer momentId;
    private List<OneMomentLike> momentLikes;
}
