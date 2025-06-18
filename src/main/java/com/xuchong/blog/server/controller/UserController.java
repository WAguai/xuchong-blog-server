package com.xuchong.blog.server.controller;

import com.xuchong.blog.common.result.Result;
import com.xuchong.blog.pojo.dto.GetVertifyCodeDTO;
import com.xuchong.blog.pojo.dto.RegisterDTO;
import com.xuchong.blog.pojo.dto.LoginDTO;
import com.xuchong.blog.server.service.UserService;
import com.xuchong.blog.server.service.VerifyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@Tag(name = "游客相关接口")
@Slf4j
public class UserController {
    @Resource
    private UserService userService;

    @Resource
    private VerifyService verifyService;

    @Operation(summary = "登录")
    @PostMapping("/login")
    public Result login(@RequestBody LoginDTO loginDTO) {
        log.info("登录{}", loginDTO);
        return userService.login(loginDTO);
    }
    @Operation(summary = "登出")
    @PostMapping("/logout")
    public Result logout() {
        log.info("登出");
        return userService.logout();
    }

    @Operation(summary = "发验证码")
    @PostMapping("/getVerifyCode")
    public Result<?> getVerifyCode(@RequestBody GetVertifyCodeDTO getVertifyCodeDTO) {
        String email = getVertifyCodeDTO.getEmail();
        return verifyService.getVerifyCode(email);
    }
    @Operation(summary = "注册")
    @PostMapping("/register")
    public Result register(@RequestBody RegisterDTO registerDTO) {
        return userService.register(registerDTO);
    }

    @Operation(summary = "获取是否为管理员")
    @GetMapping("/getIsAdmin")
    public Result<?> getIsAdmin() {
        return userService.getIsAdmin();
    }
}
