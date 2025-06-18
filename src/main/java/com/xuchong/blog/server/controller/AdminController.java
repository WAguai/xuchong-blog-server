package com.xuchong.blog.server.controller;

import com.xuchong.blog.common.result.Result;
import com.xuchong.blog.common.utils.AliOssUtil;
import com.xuchong.blog.pojo.dto.AddMomentDTO;
import com.xuchong.blog.server.service.GuestBookService;
import com.xuchong.blog.server.service.GuestCommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("admin")
@Tag(name = "管理员相关接口")
@Slf4j
public class AdminController {
    @Resource
    private AliOssUtil aliOssUtil;
    @Resource
    private GuestBookService guestBookService;
    @Resource
    private GuestCommentService guestCommentService;

    @PostMapping("/common/uploadImages")
    @Operation(summary = "文件上传")
    public Result<String> uploadImages(MultipartFile file){
        try {
            String originalFilename = file.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String objectName = UUID.randomUUID().toString() + extension;
            String uploadURL = aliOssUtil.upload(file.getBytes(), objectName);
            return Result.success(uploadURL);

        } catch (IOException e) {
            log.error("文件上传失败",e);
        }
        return Result.error("文件上传失败");
    }

    @Operation(summary = "添加说说")
    @PostMapping("/moment/addMoment")
    public Result<?> addMoment(@RequestBody AddMomentDTO addMomentDTO) {

        return null;
    }

    @Operation(summary = "删除留言")
    @DeleteMapping("/guestBook/deleteGuestBook")
    public Result<?> deleteGuestBook(@RequestParam Integer guestBookId) {

        return guestBookService.deleteGuestBook(guestBookId);
    }

    @Operation(summary = "删除留言评论")
    @DeleteMapping("/guestComment/deleteGuestComment")
    public Result<?> deleteGuestComment(@RequestParam Integer guestCommentId) {
        return guestCommentService.deleteGuestComment(guestCommentId);
    }
}
