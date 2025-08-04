package com.xuchong.blog.pojo.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UploadImageDTO {
    MultipartFile file;
    String folder;
}
