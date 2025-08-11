package com.xuchong.blog.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xuchong.blog.common.result.Result;
import com.xuchong.blog.pojo.dto.RegisterDTO;
import com.xuchong.blog.pojo.dto.LoginDTO;
import com.xuchong.blog.pojo.entity.db.User;

public interface UserService extends IService<User> {

    Result<?> register(RegisterDTO registerDTO);

    Result<?> login(LoginDTO loginDTODTO);

    Result<?> logout();

    Result<?> getIsAdmin();
}
