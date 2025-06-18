package com.xuchong.blog.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xuchong.blog.pojo.entity.GuestComment;
import com.xuchong.blog.pojo.entity.GuestComments;
import io.lettuce.core.dynamic.annotation.Param;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface GuestCommentMapper extends BaseMapper<GuestComment> {

    // 批量查询评论（按留言ID分组）
    @MapKey("guestBookId")
    Map<Integer, GuestComments> selectCommentsByGuestBookIds(
            @Param("guestBookIds") List<Integer> guestBookIds);
}
