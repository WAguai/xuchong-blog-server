package com.xuchong.blog.server.controller;


import com.xuchong.blog.common.result.Result;
import com.xuchong.blog.pojo.dto.AddGuestCommentDTO;
import com.xuchong.blog.server.service.GuestCommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("guestComment")
@Tag(name = "留言板相关接口")
@Slf4j
public class GuestCommentController {

    @Resource
    private GuestCommentService guestCommentService;

    @Operation(summary = "添加评论")
    @PostMapping("/addComment")
    public Result<?> addComments(@RequestBody AddGuestCommentDTO addGuestCommentDTO){
        log.info("添加评论");
        return guestCommentService.addComment(addGuestCommentDTO);
    }
}
