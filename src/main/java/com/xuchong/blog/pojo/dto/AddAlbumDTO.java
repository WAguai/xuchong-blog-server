package com.xuchong.blog.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "创建相册请求参数")
public class AddAlbumDTO {
    
    @NotBlank(message = "相册名称不能为空")
    @Schema(description = "相册名称", required = true)
    private String name;
    
    @Schema(description = "相册描述")
    private String description;
}