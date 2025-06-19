package org.springframework.test.common;

import cn.hutool.core.util.StrUtil;
import org.springframework.aop.AfterAdvice;

import java.lang.reflect.Method;

public class WorldServiceAfterAdvice implements AfterAdvice {

    @Override
    public void after(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("AfterAdvice: do something after the " + StrUtil.lowerFirst(target.getClass().getSimpleName()) + " " + method.getName());
    }
}
