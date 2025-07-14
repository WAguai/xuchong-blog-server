package com.xuchong.blog.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuchong.blog.pojo.entity.db.GuestBook;
import com.xuchong.blog.pojo.entity.OneGuestBook;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface GuestBookMapper extends BaseMapper<GuestBook> {
    // 分页查询留言（带用户昵称）
    @Select("SELECT gb.*, u.nick_name FROM guest_book gb " +
            "LEFT JOIN user u ON gb.user_id = u.id " +
            "ORDER BY gb.create_time DESC")
    Page<OneGuestBook> selectGuestBookPage(Page<OneGuestBook> page);


}
