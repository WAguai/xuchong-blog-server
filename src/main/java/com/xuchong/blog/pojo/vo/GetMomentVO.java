package com.xuchong.blog.pojo.vo;

import com.xuchong.blog.pojo.entity.OneGuestBook;
import com.xuchong.blog.pojo.entity.OneMoment;
import com.xuchong.blog.pojo.entity.db.Moment;
import lombok.Data;

import java.util.List;

@Data
public class GetMomentVO {
    private Long totalMoments;      // 总记录数
    private List<OneMoment> moments;
}
