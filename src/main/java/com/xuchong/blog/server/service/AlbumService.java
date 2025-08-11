package com.xuchong.blog.server.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuchong.blog.pojo.dto.AddAlbumDTO;
import com.xuchong.blog.pojo.vo.AlbumVO;

public interface AlbumService {
    
    /**
     * 创建相册
     */
    AlbumVO createAlbum(AddAlbumDTO addAlbumDTO);
    
    /**
     * 获取相册列表（分页）
     */
    Page<AlbumVO> getAlbums(Page<AlbumVO> page);
    
    /**
     * 根据ID获取相册
     */
    AlbumVO getAlbumById(Integer id);
    
    /**
     * 删除相册
     */
    void deleteAlbum(Integer id);
}