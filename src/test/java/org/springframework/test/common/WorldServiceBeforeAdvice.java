package org.springframework.test.common;

import cn.hutool.core.util.StrUtil;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class WorldServiceBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("BeforeAdvice: do something before the " + StrUtil.lowerFirst(target.getClass().getSimpleName()) + " " + method.getName());
    }
}
