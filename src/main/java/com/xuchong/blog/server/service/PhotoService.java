package com.xuchong.blog.server.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuchong.blog.pojo.dto.AddPhotoDTO;
import com.xuchong.blog.pojo.vo.PhotoVO;

public interface PhotoService {
    
    /**
     * 添加照片
     */
    PhotoVO addPhoto(AddPhotoDTO addPhotoDTO);
    
    /**
     * 获取相册中的照片（分页）
     */
    Page<PhotoVO> getPhotosByAlbumId(Page<PhotoVO> page, Integer albumId);
    
    /**
     * 根据ID获取照片
     */
    PhotoVO getPhotoById(Integer id);
    
    /**
     * 删除照片
     */
    void deletePhoto(Integer id);
}