package com.xuchong.blog.server.service.Impl;

import cn.hutool.core.util.RandomUtil;
import com.xuchong.blog.common.result.Result;
import com.xuchong.blog.server.service.VerifyService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import static com.xuchong.blog.common.constant.RedisConstant.CODE_EXPIRE_MINUTES;
import static com.xuchong.blog.common.constant.RedisConstant.REDIS_KEY_PREFIX;

@Slf4j
@Service
public class VerifyServiceImpl implements VerifyService {

    // 邮箱号正则（校验格式）
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\da-zA-Z][\\w.-]{0,62}@[\\da-zA-Z-]{1,252}(\\.[a-zA-Z]{2,})+$");

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private JavaMailSender mailSender;

    // 获取发件人邮箱
    @Value("${spring.mail.username}")
    private String sender;

    // 获取发件人昵称
    @Value("${spring.mail.nickname}")
    private String nickname;
    /**
     * 发送验证码（带手机号格式校验）
     * @param email 邮箱
     * @return 是否发送成功
     */
    public Result<?> getVerifyCode(String email) {
        // 1.校验邮箱
        if (!isValidEmail(email)) {
            log.info("邮箱格式错误");
            return Result.error("邮箱格式错误");
        }

        // 2. 生成6位随机验证码
        String code = RandomUtil.randomNumbers(6);

        // 3. 存储到Redis（key: verify_code:13800138000, value: 123456）
        String redisKey = REDIS_KEY_PREFIX + email;
        stringRedisTemplate.opsForValue().set(
                redisKey,
                code,
                CODE_EXPIRE_MINUTES,
                TimeUnit.MINUTES
        );
        // 4. 发送短信（模拟实现）
        if(sendSms(email, code))
            return Result.success();
        return Result.error("发送验证码失败");
    }

    /**
     * 验证验证码是否正确
     * @param email 邮箱号
     * @param userInput 用户输入的验证码
     * @return 验证结果
     */
    public boolean verifyCode(String email, String userInput) {
        String redisKey = REDIS_KEY_PREFIX + email;
        String storedCode = stringRedisTemplate.opsForValue().get(redisKey);
        log.info("校验验证码,{}",redisKey);

        // 1. 验证码不存在或过期
        if (storedCode == null) {
            return false;
        }

        // 2. 验证码匹配（忽略大小写）
        boolean isValid = storedCode.equalsIgnoreCase(userInput);

        // 3. 验证成功后删除Redis记录（防止重复使用）
        if (isValid) {
            stringRedisTemplate.delete(redisKey);
            return true;
        }
        return false;
    }

    //--- 工具方法 ---//
    private boolean isValidEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false; // 显式处理空值
        }
        return EMAIL_PATTERN.matcher(email).matches();
    }


    private boolean sendSms(String email, String code) {
        log.info("发送到 {} 的验证码:{}",email, code);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(nickname + '<' + sender + '>');
        message.setTo(email);
        message.setSubject("欢迎来到羽中羽中的小站(●'◡'●)");

        String content = "【验证码】您的验证码为：" + code + " 。 验证码五分钟内有效，逾期作废。\n\n\n" +
                "------------------------------\n\n\n" ;

        message.setText(content);
        mailSender.send(message);

        return true;
    }
}
