package org.springframework.test.common;

import cn.hutool.core.util.StrUtil;
import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;

public class WorldServiceThrowsAdvice implements ThrowsAdvice {

    @Override
    public void throwsHandle(Throwable throwable, Method method, Object[] args, Object target) {
        System.out.println("ThrowsAdvice: do something before the " + StrUtil.lowerFirst(target.getClass().getSimpleName()) + " " + method.getName() + " error message:" + throwable.getMessage());
    }
}
