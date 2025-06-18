package com.xuchong.blog.common.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.WeakKeyException;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Map;

public class JwtUtil {
    /**
     * 生成JWT（新版API）
     * @param secretKey jwt秘钥（长度需≥256位）
     * @param ttlMillis jwt过期时间(毫秒)
     * @param claims 设置的信息
     */
    public static String createJWT(String secretKey, long ttlMillis, Map<String, Object> claims) {
        // 验证密钥长度（HS256要求≥256位）
        if (secretKey.getBytes(StandardCharsets.UTF_8).length < 32) {
            throw new WeakKeyException("HS256算法需要≥256位的密钥");
        }

        // 生成安全密钥对象
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

        // 过期时间计算
        long expMillis = System.currentTimeMillis() + ttlMillis;
        Date exp = new Date(expMillis);

        return Jwts.builder()
                .claims(claims)          // 设置声明
                .expiration(exp)         // 设置过期时间
                .signWith(key)            // 签名（自动识别算法）
                .compact();
    }

    /**
     * Token解密（新版API）
     * @param secretKey jwt秘钥
     * @param token 加密后的token
     */
    public static Claims parseJWT(String secretKey, String token) {
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

        return Jwts.parser()
                .verifyWith(key)         // 设置验证密钥
                .build()
                .parseSignedClaims(token) // 解析签名声明
                .getPayload();            // 获取负载数据
    }
}