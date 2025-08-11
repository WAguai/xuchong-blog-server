package com.xuchong.blog.server.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuchong.blog.pojo.dto.AddAlbumDTO;
import com.xuchong.blog.pojo.vo.AlbumVO;
import com.xuchong.blog.server.service.AlbumService;
import com.xuchong.blog.common.result.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("album")
@Tag(name = "相册管理")
@Slf4j
@RequiredArgsConstructor
public class AlbumController {
    
    private final AlbumService albumService;
    
    @PostMapping("/add")
    @Operation(summary = "创建相册")
    public Result<AlbumVO> createAlbum(@Valid @RequestBody AddAlbumDTO addAlbumDTO) {
        log.info("创建相册: {}", addAlbumDTO);
        AlbumVO albumVO = albumService.createAlbum(addAlbumDTO);
        return Result.success(albumVO);
    }
    
    @GetMapping("/page")
    @Operation(summary = "获取相册列表")
    public Result<Page<AlbumVO>> getAlbums(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        log.info("获取相册列表，第 {} 页，每页 {} 条", page, size);
        Page<AlbumVO> albumPage = new Page<>(page, size);
        Page<AlbumVO> result = albumService.getAlbums(albumPage);
        return Result.success(result);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "根据ID获取相册")
    public Result<AlbumVO> getAlbumById(@PathVariable Integer id) {
        log.info("获取相册: {}", id);
        AlbumVO albumVO = albumService.getAlbumById(id);
        return Result.success(albumVO);
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "删除相册")
    public Result<Void> deleteAlbum(@PathVariable Integer id) {
        log.info("删除相册: {}", id);
        albumService.deleteAlbum(id);
        return Result.success();
    }
}