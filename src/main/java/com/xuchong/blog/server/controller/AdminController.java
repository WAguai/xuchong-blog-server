package com.xuchong.blog.server.controller;

import com.xuchong.blog.common.context.BaseContext;
import com.xuchong.blog.common.result.Result;
import com.xuchong.blog.common.utils.AliOssUtil;
import com.xuchong.blog.pojo.dto.AddMomentDTO;
import com.xuchong.blog.pojo.entity.db.User;
import com.xuchong.blog.server.service.*;
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
    private UserService userService;
    @Resource
    private OssService ossService;


    @Operation(summary = "添加说说")
    @PostMapping("/moment/addMoment")
    public Result<?> addMoment(@RequestBody AddMomentDTO addMomentDTO) {
        if (!isAdmin())
            return Result.error("不是管理员");
        return momentService.addMoment(addMomentDTO);
    }

    @Operation(summary = "更新说说")
    @PutMapping("/moment/updateMoment")
    public Result<?> updateMoment(@RequestBody AddMomentDTO addMomentDTO) {
        if (!isAdmin())
            return Result.error("不是管理员");
        return momentService.updateMoment(addMomentDTO);
    }

    @Operation(summary = "删除留言")
    @DeleteMapping("/guestBook/deleteGuestBook")
    public Result<?> deleteGuestBook(@RequestParam Integer guestBookId) {
        if (!isAdmin())
            return Result.error("不是管理员");
        return guestBookService.deleteGuestBook(guestBookId);
    }

    @Operation(summary = "删除留言评论")
    @DeleteMapping("/guestComment/deleteGuestComment")
    public Result<?> deleteGuestComment(@RequestParam Integer guestCommentId) {
        if (!isAdmin())
            return Result.error("不是管理员");
        return guestCommentService.deleteGuestComment(guestCommentId);
    }

    @Operation(summary = "管理员获取说说详情")
    @GetMapping("/moment/getMomentDetails/{id}")
    public Result<?> getMomentDetails(@PathVariable Integer id) {
        if (!isAdmin())
            return Result.error("不是管理员");
        return momentService.getMomentDetails(id);
    }

    private Boolean isAdmin(){
        Integer userId = BaseContext.getCurrentId();
        User user = userService.query().eq("id", userId).one();
        return user != null && user.getIsAdmin().equals("1");
    }


    @Operation(summary = "上传图片")
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
}
