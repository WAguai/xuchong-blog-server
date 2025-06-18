package com.xuchong.blog.server.controller;

import com.xuchong.blog.common.result.Result;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("moment")
@Tag(name = "说说相关接口")
@Slf4j
public class MomentController {

    @GetMapping("/getMoments")
    public Result<?> getMoments(){

    }

}
