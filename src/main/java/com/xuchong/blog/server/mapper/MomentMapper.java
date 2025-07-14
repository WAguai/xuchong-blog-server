package com.xuchong.blog.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuchong.blog.pojo.entity.OneGuestBook;
import com.xuchong.blog.pojo.entity.OneMoment;
import com.xuchong.blog.pojo.entity.OneMomentComment;
import com.xuchong.blog.pojo.entity.db.Moment;
import io.lettuce.core.dynamic.annotation.Param;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.sql.Wrapper;
import java.util.List;
import java.util.Map;

@Mapper
public interface MomentMapper extends BaseMapper<Moment> {
    // 分页查询说说基础信息

    @Select("SELECT m.*, u.nick_name FROM moment m " +
            "LEFT JOIN user u ON m.user_id = u.id " +
            "ORDER BY m.create_time DESC")
    IPage<OneMoment> selectMomentPage(IPage<OneMoment> page,
                                   @Param(Constants.WRAPPER) Wrapper wrapper);

}