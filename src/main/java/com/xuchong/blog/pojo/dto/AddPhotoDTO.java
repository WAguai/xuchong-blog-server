package com.xuchong.blog.pojo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Schema(description = "添加照片请求参数")
public class AddPhotoDTO {
    
    @NotNull(message = "相册ID不能为空")
    @Schema(description = "相册ID", required = true)
    private Integer albumId;
    
    @NotNull(message = "图片URL不能为空")
    @Schema(description = "图片URL", required = true)
    private String url;
    
    @Schema(description = "照片名称")
    private String name;
    
    @Schema(description = "照片描述")
    private String description;
    
    @Schema(description = "拍摄时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate shootTime;
    
    @Schema(description = "拍摄地点")
    private String shootLocation;
}