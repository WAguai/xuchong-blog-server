package com.xuchong.blog.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuchong.blog.pojo.entity.db.Album;
import com.xuchong.blog.pojo.vo.AlbumVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AlbumMapper extends BaseMapper<Album> {
    

    @Select("SELECT  a.id,a.name,a.description,a.create_time,a.update_time, COUNT(p.id) as photoCount, " +
            "(SELECT p2.url FROM photo p2 WHERE p2.album_id = a.id ORDER BY p2.create_time ASC LIMIT 1) as coverUrl " +
            "FROM album a " +
            "LEFT JOIN photo p ON a.id = p.album_id " +
            "GROUP BY a.id,a.name,a.description,a.create_time,a.update_time " +
            "ORDER BY a.create_time DESC")
    Page<AlbumVO> getAlbumsWithPhotoCount(Page<AlbumVO> page);
}