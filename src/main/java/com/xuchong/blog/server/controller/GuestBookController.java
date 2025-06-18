package com.xuchong.blog.server.controller;

import com.xuchong.blog.common.result.Result;
import com.xuchong.blog.pojo.dto.AddGuestBookMessageDTO;
import com.xuchong.blog.server.service.GuestBookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("guestBook")
@Tag(name = "留言板相关接口")
@Slf4j
public class GuestBookController {
    @Resource
    private GuestBookService guestBookService;

    @PostMapping("/addMessage")
    @Operation(summary = "添加留言")
    public Result<?> addMessage(@RequestBody AddGuestBookMessageDTO addGuestBookMessageDTO) {
        log.info("添加留言,{}", addGuestBookMessageDTO);
        return guestBookService.addMessage(addGuestBookMessageDTO);
    }

    @Operation(summary = "获取留言")
    @GetMapping("/getMessages")
    public Result<?> getMessages(
            @RequestParam(defaultValue = "1") Integer currentPage,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        return guestBookService.getMessages(currentPage,pageSize);
    }

}
