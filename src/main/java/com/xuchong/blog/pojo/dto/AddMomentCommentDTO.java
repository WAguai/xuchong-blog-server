package com.xuchong.blog.pojo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class AddMomentCommentDTO implements Serializable {
    private Integer momentId;
    private String content;
}