package org.springframework.test.advice;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class UserServiceBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("BeforeAdvice: do something before the userServiceBeforeAdvice " + method.getName());
    }
}
