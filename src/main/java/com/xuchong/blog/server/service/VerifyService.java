package com.xuchong.blog.server.service;

import com.xuchong.blog.common.result.Result;

public interface VerifyService {

    Result<?> getVerifyCode(String email);

    boolean verifyCode(String email, String code);
}
