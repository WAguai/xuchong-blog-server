package com.xuchong.blog.common.context;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class BaseContext {

    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
    @Getter
    @Setter
    private static Boolean isAdmin = false;

    public static void setCurrentId(Integer id) {
        threadLocal.set(id);
        log.info("设置用户id,{}",id);
    }

    public static Integer getCurrentId() {
        log.info("获取用户id,{}",threadLocal.get());
        return threadLocal.get();
    }

    public static void removeCurrentId() {
        threadLocal.remove();
    }

}
