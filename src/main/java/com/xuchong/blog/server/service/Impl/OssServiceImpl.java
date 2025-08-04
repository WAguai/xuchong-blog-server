package com.xuchong.blog.server.service.Impl;

import com.xuchong.blog.common.properties.AliOssProperties;
import com.xuchong.blog.common.result.Result;
import com.xuchong.blog.common.utils.AliOssUtil;
import com.xuchong.blog.pojo.vo.UploadImageVO;
import com.xuchong.blog.server.service.OssService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@Slf4j
public class OssServiceImpl implements OssService {

    @Autowired
    private AliOssProperties aliOssProperties;

    @Override
    public Result<UploadImageVO> uploadImage(MultipartFile file,String folder) {
        try {
            log.info("上传文件");
            // 验证文件类型
            String contentType = file.getContentType();
            if (contentType == null || !contentType.startsWith("image/")) {
                throw new RuntimeException("请上传图片文件");
            }

            // 验证文件大小 (最大5MB)
            long maxSize = 20 * 1024 * 1024;
            if (file.getSize() > maxSize) {
                throw new RuntimeException("图片大小不能超过20MB");
            }

            // 生成文件名
            String originalFilename = file.getOriginalFilename();
            String fileExtension = originalFilename != null ? 
                originalFilename.substring(originalFilename.lastIndexOf(".")) : ".jpg";
            
            String ossFileName = folder + "/" + UUID.randomUUID() + fileExtension;

            // 上传到OSS
            AliOssUtil aliOssUtil = new AliOssUtil(
                    aliOssProperties.getEndpoint(),
                    aliOssProperties.getAccessKeyId(),
                    aliOssProperties.getAccessKeySecret(),
                    aliOssProperties.getBucketName()
            );
            String url = aliOssUtil.upload(file.getBytes(), ossFileName);
            UploadImageVO uploadImageVO = new UploadImageVO();
            uploadImageVO.setFilename(originalFilename);
            uploadImageVO.setUrl(url);
            return Result.success(uploadImageVO);
        } catch (IOException e) {
            return Result.error("文件上传失败");
        }
    }

    @Override
    public Result<?> delete(String fileName) {
        try {
            AliOssUtil aliOssUtil = new AliOssUtil(
                    aliOssProperties.getEndpoint(),
                    aliOssProperties.getAccessKeyId(),
                    aliOssProperties.getAccessKeySecret(),
                    aliOssProperties.getBucketName()
            );
            aliOssUtil.delete(fileName);
            return Result.success();
        } catch (Exception e) {
            log.error("删除文件失败", e);
            return Result.error("删除文件失败");
        }
    }
}