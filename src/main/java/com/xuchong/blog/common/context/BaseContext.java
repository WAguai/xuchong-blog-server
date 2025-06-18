package com.xuchong.blog.common.context;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class BaseContext {

    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();
    @Getter
    @Setter
    private static Boolean isAdmin = false;

    public static void setCurrentId(Long id) {
        threadLocal.set(id);
    }

    public static Long getCurrentId() {
        return threadLocal.get();
    }

    public static void removeCurrentId() {
        threadLocal.remove();
    }

}
