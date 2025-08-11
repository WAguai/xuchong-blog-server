package com.xuchong.blog.server.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuchong.blog.pojo.dto.AddPhotoDTO;
import com.xuchong.blog.pojo.vo.PhotoVO;
import com.xuchong.blog.server.service.PhotoService;
import com.xuchong.blog.common.result.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("photo")
@Tag(name = "照片管理")
@Slf4j
@RequiredArgsConstructor
public class PhotoController {
    
    private final PhotoService photoService;
    
    @PostMapping("/add")
    @Operation(summary = "添加照片")
    public Result<PhotoVO> addPhoto(@RequestBody AddPhotoDTO addPhotoDTO) {
        log.info("添加照片: {}", addPhotoDTO);
        PhotoVO photoVO = photoService.addPhoto(addPhotoDTO);
        return Result.success(photoVO);
    }
    
    @GetMapping("/page")
    @Operation(summary = "获取相册中的照片")
    public Result<Page<PhotoVO>> getPhotosByAlbumId(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam Integer albumId) {
        log.info("获取相册 {} 中的照片，第 {} 页，每页 {} 条", albumId, page, size);
        Page<PhotoVO> photoPage = new Page<>(page, size);
        Page<PhotoVO> result = photoService.getPhotosByAlbumId(photoPage, albumId);
        return Result.success(result);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "根据ID获取照片")
    public Result<PhotoVO> getPhotoById(@PathVariable Integer id) {
        log.info("获取照片: {}", id);
        PhotoVO photoVO = photoService.getPhotoById(id);
        return Result.success(photoVO);
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "删除照片")
    public Result<Void> deletePhoto(@PathVariable Integer id) {
        log.info("删除照片: {}", id);
        photoService.deletePhoto(id);
        return Result.success();
    }
}
