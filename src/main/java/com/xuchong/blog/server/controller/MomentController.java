package com.xuchong.blog.server.controller;

import com.xuchong.blog.common.result.Result;
import com.xuchong.blog.server.service.MomentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("moment")
@Tag(name = "说说相关接口")
@Slf4j
public class MomentController {
    @Resource
    private MomentService momentService;

    @GetMapping("/getMoments")
    public Result<?> getMoments(
            @RequestParam(defaultValue = "1") Integer currentPage,
            @RequestParam(defaultValue = "10") Integer pageSize){
        return momentService.getMoments(currentPage,pageSize);
    }

    @GetMapping("/getMomentDetails/{id}")
    public Result<?> getMomentDetails(@PathVariable("id") Integer id){
        return momentService.getMomentDetails(id);
    }

    @PostMapping("/likeOrDislike/{momentId}")
    public Result<?> likeOrDislike(@PathVariable("momentId") Integer momentId){
        return momentService.likeOrDislike(momentId);
    }


}
