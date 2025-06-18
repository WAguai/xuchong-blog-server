package com.xuchong.blog.common.constant;

public class RedisConstant {

    // Redis 键前缀（防止键冲突）
    public static final String REDIS_KEY_PREFIX = "verify_code:";

    // 验证码有效期（5分钟）
    public static final long CODE_EXPIRE_MINUTES = 5;
}
