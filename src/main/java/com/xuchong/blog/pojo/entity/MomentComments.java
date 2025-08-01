package com.xuchong.blog.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MomentComments {
    private Integer momentId;
    private List<OneMomentComment> momentComments;
}
