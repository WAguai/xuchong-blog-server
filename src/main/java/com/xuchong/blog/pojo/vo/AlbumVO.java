package com.xuchong.blog.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(description = "相册信息响应")
public class AlbumVO {
    
    @Schema(description = "相册ID")
    private Integer id;
    
    @Schema(description = "相册名称")
    private String name;
    
    @Schema(description = "相册描述")
    private String description;
    
    @Schema(description = "创建时间")
    private LocalDateTime createTime;
    
    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
    
    @Schema(description = "照片数量")
    private Integer photoCount;
    
    @Schema(description = "封面图片URL")
    private String coverUrl;
}