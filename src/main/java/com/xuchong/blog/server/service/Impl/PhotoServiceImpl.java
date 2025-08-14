package com.xuchong.blog.server.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuchong.blog.pojo.dto.AddPhotoDTO;
import com.xuchong.blog.pojo.entity.db.MomentLike;
import com.xuchong.blog.pojo.entity.db.Photo;
import com.xuchong.blog.pojo.vo.PhotoVO;
import com.xuchong.blog.server.mapper.PhotoMapper;
import com.xuchong.blog.server.service.PhotoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
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
    public String deletePhoto(Integer id) {
        Photo photo = photoMapper.selectById(id);
        if(photo!=null){
            photoMapper.deleteById(id);
            return photo.getUrl();
        }
        return null;
    }

    @Override
    public List<String> getRecentPhotos(Integer num) {
        // 1. 构建查询条件
        QueryWrapper<Photo> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("create_time");

        // 2. 指定要查询的字段
        queryWrapper.select("url");

        // 3. 设置分页，获取第一页的指定数量记录
        queryWrapper.last("LIMIT " + num);

        // 4. 执行查询，直接返回 List<String>
        List<String> urls = photoMapper.selectObjs(queryWrapper);
        log.info("获取到urls,{}", urls);

        // 5. 检查并复制 URL 以填充列表
        if (urls.size() < num && !urls.isEmpty()) {
            int originalSize = urls.size();
            int currentIndex = 0;
            while (urls.size() < num) {
                // 循环从原始列表中复制 URL
                urls.add(urls.get(currentIndex));
                currentIndex = (currentIndex + 1) % originalSize;
            }
        }
        return urls;
    }
}