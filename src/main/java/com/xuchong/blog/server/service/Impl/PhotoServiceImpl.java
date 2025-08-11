package com.xuchong.blog.server.service.Impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuchong.blog.pojo.dto.AddPhotoDTO;
import com.xuchong.blog.pojo.entity.db.Photo;
import com.xuchong.blog.pojo.vo.PhotoVO;
import com.xuchong.blog.server.mapper.PhotoMapper;
import com.xuchong.blog.server.service.PhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PhotoServiceImpl implements PhotoService {
    
    private final PhotoMapper photoMapper;
    
    @Override
    public PhotoVO addPhoto(AddPhotoDTO addPhotoDTO) {
        Photo photo = new Photo();
        photo.setAlbumId(addPhotoDTO.getAlbumId());
        photo.setUrl(addPhotoDTO.getUrl());
        photo.setName(addPhotoDTO.getName());
        photo.setDescription(addPhotoDTO.getDescription());
        photo.setShootTime(addPhotoDTO.getShootTime());
        photo.setShootLocation(addPhotoDTO.getShootLocation());
        photo.setCreateTime(LocalDateTime.now());
        photo.setUpdateTime(LocalDateTime.now());
        
        photoMapper.insert(photo);
        
        PhotoVO photoVO = new PhotoVO();
        BeanUtils.copyProperties(photo, photoVO);
        
        return photoVO;
    }
    
    @Override
    public Page<PhotoVO> getPhotosByAlbumId(Page<PhotoVO> page, Integer albumId) {
        return photoMapper.getPhotosByAlbumId(page, albumId);
    }
    
    @Override
    public PhotoVO getPhotoById(Integer id) {
        Photo photo = photoMapper.selectById(id);
        if (photo == null) {
            return null;
        }
        
        PhotoVO photoVO = new PhotoVO();
        BeanUtils.copyProperties(photo, photoVO);
        return photoVO;
    }
    
    @Override
    public void deletePhoto(Integer id) {
        photoMapper.deleteById(id);
    }
}