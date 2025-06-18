package com.xuchong.blog.server.service;

import com.xuchong.blog.common.result.Result;
import com.xuchong.blog.pojo.dto.RegisterDTO;
import com.xuchong.blog.pojo.dto.LoginDTO;

public interface UserService {

    Result<?> register(RegisterDTO registerDTO);

    Result<?> login(LoginDTO loginDTODTO);

    Result<?> logout();

    Result<?> getIsAdmin();
}
