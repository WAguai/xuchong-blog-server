package com.xuchong.blog.server.controller;

import com.xuchong.blog.common.result.Result;
import com.xuchong.blog.common.utils.AliOssUtil;
import com.xuchong.blog.pojo.dto.AddMomentDTO;
import com.xuchong.blog.server.service.GuestBookService;
import com.xuchong.blog.server.service.GuestCommentService;
import com.xuchong.blog.server.service.MomentService;
import com.xuchong.blog.server.service.OssService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
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
    private GuestBookService guestBookService;
    @Resource
    private GuestCommentService guestCommentService;
    @Resource
    private MomentService momentService;
    @Resource
    private OssService ossService;

    @Operation(summary = "添加说说")
    @PostMapping("/moment/addMoment")
    public Result<?> addMoment(@RequestBody AddMomentDTO addMomentDTO) {
        return momentService.addMoment(addMomentDTO);
    }

    @Operation(summary = "更新说说")
    @PutMapping("/moment/updateMoment")
    public Result<?> updateMoment(@RequestBody AddMomentDTO addMomentDTO) {
        return momentService.updateMoment(addMomentDTO);
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

    @PostMapping("/common/uploadImage")
    public Result<?> uploadImage(@RequestParam MultipartFile file,
                                 @RequestParam(defaultValue = "default") String folder) {
        return ossService.uploadImage(file,folder);
    }

    @Operation(summary = "删除上传的图片")
    @DeleteMapping("/common/deleteImage")
    public Result<?> deleteImage(@RequestParam String fileName) {
        return ossService.delete(fileName);
    }

    @Operation(summary = "管理员获取说说详情")
    @GetMapping("/moment/getMomentDetails/{id}")
    public Result<?> getMomentDetails(@PathVariable Integer id) {
        return momentService.getMomentDetails(id);
    }
}
