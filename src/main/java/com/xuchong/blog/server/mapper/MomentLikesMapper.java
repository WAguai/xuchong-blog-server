package com.xuchong.blog.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xuchong.blog.pojo.entity.MomentLikes;
import com.xuchong.blog.pojo.entity.db.MomentLike;
import io.lettuce.core.dynamic.annotation.Param;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface MomentLikesMapper  extends BaseMapper<MomentLike> {

    @MapKey("momentId")
    Map<Integer, MomentLikes> selectLikesByMomentIds(@Param("momentIds") List<Integer> momentIds);
}
