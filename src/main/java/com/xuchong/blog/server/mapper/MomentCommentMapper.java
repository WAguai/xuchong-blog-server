package com.xuchong.blog.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xuchong.blog.pojo.entity.MomentComments;
import com.xuchong.blog.pojo.entity.OneMomentComment;
import com.xuchong.blog.pojo.entity.db.GuestBook;
import com.xuchong.blog.pojo.entity.db.MomentComment;
import io.lettuce.core.dynamic.annotation.Param;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

public interface MomentCommentMapper  extends BaseMapper<MomentComment> {

    @MapKey("momentId")
    Map<Integer, MomentComments>
        selectCommentsByMomentIds(@Param("momentIds") List<Integer> momentIds);

}
