package com.xuchong.blog.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Schema(description = "照片信息响应")
public class PhotoVO {
    
    @Schema(description = "照片ID")
    private Integer id;
    
    @Schema(description = "相册ID")
    private Integer albumId;
    
    @Schema(description = "图片URL")
    private String url;
    
    @Schema(description = "照片名称")
    private String name;
    
    @Schema(description = "照片描述")
    private String description;
    
    @Schema(description = "拍摄时间")
    private LocalDate shootTime;
    
    @Schema(description = "拍摄地点")
    private String shootLocation;
    
    @Schema(description = "创建时间")
    private LocalDateTime createTime;
    
    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
}