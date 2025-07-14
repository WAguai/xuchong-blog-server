package com.xuchong.blog.server.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuchong.blog.common.context.BaseContext;
import com.xuchong.blog.common.properties.JwtProperties;
import com.xuchong.blog.common.result.Result;
import com.xuchong.blog.common.utils.JwtUtil;
import com.xuchong.blog.pojo.dto.LoginDTO;
import com.xuchong.blog.pojo.dto.RegisterDTO;
import com.xuchong.blog.pojo.entity.db.User;
import com.xuchong.blog.common.constant.JwtClaimsConstant;
import com.xuchong.blog.pojo.vo.LoginVO;
import com.xuchong.blog.server.mapper.UserMapper;
import com.xuchong.blog.server.service.UserService;
import com.xuchong.blog.server.service.VerifyService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {
    @Resource
    private VerifyService verifyService;

    @Resource
    private JwtProperties jwtProperties;

    @Override
    public Result<?> register(RegisterDTO registerDTO) {
        // 1. 校验昵称重复
        if (checkNickNameExists(registerDTO.getNickName())) {
            return Result.error("昵称已经存在");
        }

        // 2. 校验邮箱重复
        if (checkEmailExists(registerDTO.getEmail())) {
            return Result.error("email已注册");
        }
        // 3.校验验证码
        if(!verifyService.verifyCode(registerDTO.getEmail(), registerDTO.getCode())){
            return Result.error("验证码错误或过期");
        }

        // 4. 创建用户实体
        User user = new User();
        BeanUtils.copyProperties(registerDTO, user);

        // 5. 密码加密处理（重要！）
//        user.setPassword(BCrypt.hashpw(registerDTO.getPassword(), BCrypt.gensalt()));
        user.setPassword(registerDTO.getPassword());

        // 6. 设置默认值
        user.setIsAdmin("0"); // 默认普通用户
        user.setVisitTimes(0); // 初始访问次数
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());

        // 7. 写入数据库
        this.save(user);
        LoginVO userLoginVO = generateJWTToken(user);
        BaseContext.setCurrentId(user.getId());
        BaseContext.setIsAdmin(user.getIsAdmin().equals("1"));
        return Result.success(userLoginVO);
    }

    @Override
    public Result<?> login(LoginDTO loginDTO) {
        if(BaseContext.getCurrentId() != null){
            return Result.error("已登录，请先退出");
        }
        String email = loginDTO.getEmail();
        String password = loginDTO.getPassword();
        User user = query().eq("email", email).eq("password", password).one();
        // 判断用户是否存在
        if (user == null) {
            // 不存在，创建用户
            return Result.error("账户或密码错误");
        }
        LoginVO userLoginVO = generateJWTToken(user);
        BaseContext.setCurrentId(user.getId());
        BaseContext.setIsAdmin(user.getIsAdmin().equals("1"));
        return Result.success(userLoginVO);
    }

    @Override
    public Result<?> logout() {
        BaseContext.removeCurrentId();
        BaseContext.setIsAdmin(false);
        log.info("登出成功");
        return Result.success();
    }

    @Override
    public Result<?> getIsAdmin() {
        Integer userId = BaseContext.getCurrentId();
        User user = query().eq("id", userId).one();
        if(user == null){
            return Result.error("用户不存在");
        }
        if(user.getIsAdmin().equals("1")){
            return Result.success(true);
        }
        return Result.success(false);
    }


    private LoginVO generateJWTToken(User user) {
        // 为用户生成jwt令牌
        Map<String,Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID,user.getId());
        claims.put(JwtClaimsConstant.USER_NICK_NAME,user.getNickName());
//        claims.put(JwtClaimsConstant.IS_ADMIN,user.getIsAdmin());
        String token;
        token = JwtUtil.createJWT(jwtProperties.getSecretKey(), jwtProperties.getTtl(), claims);
        return LoginVO.builder()
            .id(user.getId())
            .token(token)
            .build();
    }

    /**
     * 检查昵称是否存在
     */
    private boolean checkNickNameExists(String nickName) {
        return this.count(new QueryWrapper<User>()
                .eq("nick_name", nickName)) > 0;
    }

    /**
     * 检查邮箱是否存在
     */
    private boolean checkEmailExists(String email) {
        return this.count(new QueryWrapper<User>()
                .eq("email", email)) > 0;
    }
}
