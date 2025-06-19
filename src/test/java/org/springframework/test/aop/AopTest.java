package org.springframework.test.aop;

import cn.hutool.core.util.ClassUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.AdvisedSupport;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.TargetSource;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.CglibAopProxy;
import org.springframework.aop.framework.JdkDynamicAopProxy;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.framework.adapter.MethodBeforeAdviceInterceptor;
import org.springframework.test.advice.UserServiceBeforeAdvice;
import org.springframework.test.interceptor.UserServiceInterceptor;
import org.springframework.test.service.HelloService;
import org.springframework.test.service.UserService;
import org.springframework.test.service.UserServiceImpl;

public class AopTest {

    private AdvisedSupport advisedSupport;

    @BeforeEach
    void setUp() {
        advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(new TargetSource(new UserServiceImpl()));
        MethodMatcher methodMatcher = new AspectJExpressionPointcut("execution(* org.springframework.test.service.UserService.*(..))");
        advisedSupport.setMethodMatcher(methodMatcher);
        advisedSupport.setMethodInterceptor(new UserServiceInterceptor());
    }
    
    @Test
    void testBeforeAdvice() {
        advisedSupport.setMethodInterceptor(new MethodBeforeAdviceInterceptor(new UserServiceBeforeAdvice()));
        UserService proxy = (UserService) new ProxyFactory(advisedSupport).getProxy();
        String res = proxy.register("alice");
        System.out.println(res);
    }

    @Test
    void testProxyFactory() {
        Object proxy = new ProxyFactory(advisedSupport).getProxy();
        Assertions.assertTrue(proxy.getClass().getName().contains("Proxy"));
        advisedSupport.setProxyTargetClass(true);
        proxy = new ProxyFactory(advisedSupport).getProxy();
        Assertions.assertTrue(proxy.getClass().getName().contains("$$"));
    }

    @Test
    void testJdkDynamicProxy() {
        UserService proxy = (UserService) new JdkDynamicAopProxy(advisedSupport).getProxy();
        String res = proxy.register("alice");
        Assertions.assertNotNull(res);
        Assertions.assertTrue(proxy.getClass().getName().contains("Proxy"));
    }

    @Test
    void testCglibProxy2() {
        advisedSupport.setTargetSource(new TargetSource(new HelloService()));
        HelloService proxy = (HelloService) new CglibAopProxy(advisedSupport).getProxy();
        String res = proxy.sayHello("alice");
        Assertions.assertNotNull(res);
        Assertions.assertTrue(proxy.getClass().getName().contains("$$"));
    }

    @Test
    void testCglibProxy() {
        UserService proxy = (UserService) new CglibAopProxy(advisedSupport).getProxy();
        String res = proxy.register("alice");
        Assertions.assertNotNull(res);
        Assertions.assertTrue(proxy.getClass().getName().contains("$$"));
    }

    @Test
    void testAop() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut("execution(* org.springframework.test.service.UserService.*(..))");
        Assertions.assertTrue(pointcut.matches(UserService.class));
        Assertions.assertTrue(pointcut.matches(ClassUtil.getPublicMethod(UserService.class, "getUserInfo")));
    }
}
