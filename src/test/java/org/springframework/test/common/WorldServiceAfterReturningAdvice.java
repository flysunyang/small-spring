package org.springframework.test.common;

import cn.hutool.core.util.StrUtil;
import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

public class WorldServiceAfterReturningAdvice implements AfterReturningAdvice {

    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("AfterReturningAdvice: do something afterReturn the " + StrUtil.lowerFirst(target.getClass().getSimpleName()) + " " + method.getName() + " returnValues:" + returnValue);
    }
}
