package com.xuchong.blog.server.controller;

import com.xuchong.blog.common.result.Result;
import com.xuchong.blog.pojo.dto.AddMomentCommentDTO;
import com.xuchong.blog.server.service.MomentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("momentComment")
@Tag(name = "说说评论相关接口")
public class MomentCommentController {
    
    @Resource
    private MomentService momentService;

    @PostMapping("/add")
    public Result<?> addMomentComment(@RequestBody AddMomentCommentDTO addMomentCommentDTO) {
        return momentService.addMomentComment(addMomentCommentDTO);
    }
}