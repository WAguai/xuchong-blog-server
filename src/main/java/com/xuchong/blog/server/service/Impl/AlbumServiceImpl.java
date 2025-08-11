package com.xuchong.blog.server.service.Impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuchong.blog.pojo.dto.AddAlbumDTO;
import com.xuchong.blog.pojo.entity.db.Album;
import com.xuchong.blog.pojo.entity.db.GuestBook;
import com.xuchong.blog.pojo.vo.AlbumVO;
import com.xuchong.blog.server.mapper.AlbumMapper;
import com.xuchong.blog.server.mapper.GuestBookMapper;
import com.xuchong.blog.server.service.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AlbumServiceImpl extends ServiceImpl<AlbumMapper, Album> implements AlbumService {
    
    private final AlbumMapper albumMapper;
    
    @Override
    public AlbumVO createAlbum(AddAlbumDTO addAlbumDTO) {
        Album album = new Album();
        album.setName(addAlbumDTO.getName());
        album.setDescription(addAlbumDTO.getDescription());
        album.setCreateTime(LocalDateTime.now());
        album.setUpdateTime(LocalDateTime.now());
        
        albumMapper.insert(album);
        
        AlbumVO albumVO = new AlbumVO();
        BeanUtils.copyProperties(album, albumVO);
        albumVO.setPhotoCount(0);
        
        return albumVO;
    }
    
    @Override
    public Page<AlbumVO> getAlbums(Page<AlbumVO> page) {
        return albumMapper.getAlbumsWithPhotoCount(page);
    }
    
    @Override
    public AlbumVO getAlbumById(Integer id) {
        Album album = albumMapper.selectById(id);
        if (album == null) {
            return null;
        }
        
        AlbumVO albumVO = new AlbumVO();
        BeanUtils.copyProperties(album, albumVO);
        return albumVO;
    }
    
    @Override
    public void deleteAlbum(Integer id) {
        albumMapper.deleteById(id);
    }
}