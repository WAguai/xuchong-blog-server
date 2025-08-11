package com.xuchong.blog.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuchong.blog.pojo.entity.db.Photo;
import com.xuchong.blog.pojo.vo.PhotoVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface PhotoMapper extends BaseMapper<Photo> {
    
    @Select("SELECT * FROM photo WHERE album_id = #{albumId} ORDER BY create_time DESC")
    Page<PhotoVO> getPhotosByAlbumId(Page<PhotoVO> page, Integer albumId);
}