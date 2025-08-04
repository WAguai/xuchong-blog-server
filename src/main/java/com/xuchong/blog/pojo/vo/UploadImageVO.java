package com.xuchong.blog.pojo.vo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UploadImageVO {
    String id;
    String url;
    String filename;
}
