package com.xuchong.blog.server.service;

import com.xuchong.blog.common.result.Result;
import org.springframework.web.multipart.MultipartFile;

public interface OssService {
    Result<?> uploadImage(MultipartFile file,String folder);

    Result<?> delete(String fileName);
}
