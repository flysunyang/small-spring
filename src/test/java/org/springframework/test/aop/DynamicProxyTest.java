package org.springframework.test.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.AdvisedSupport;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.GenericInterceptor;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.TargetSource;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import org.springframework.aop.framework.CglibAopProxy;
import org.springframework.aop.framework.JdkDynamicAopProxy;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.framework.adapter.MethodBeforeAdviceInterceptor;
import org.springframework.test.common.WorldServiceAfterAdvice;
import org.springframework.test.common.WorldServiceAfterReturningAdvice;
import org.springframework.test.common.WorldServiceBeforeAdvice;
import org.springframework.test.common.WorldServiceInterceptor;
import org.springframework.test.common.WorldServiceThrowsAdvice;
import org.springframework.test.service.WorldService;
import org.springframework.test.service.WorldServiceImpl;

public class DynamicProxyTest {
    
    private AdvisedSupport advisedSupport;
    
    @BeforeEach
    void setup() {
        WorldService worldService = new WorldServiceImpl();

        advisedSupport = new AdvisedSupport();
        TargetSource targetSource = new TargetSource(worldService);
        WorldServiceInterceptor methodInterceptor = new WorldServiceInterceptor();
        MethodMatcher methodMatcher = new AspectJExpressionPointcut("execution(* org.springframework.test.service.WorldService.explode(..))");
        advisedSupport.setTargetSource(targetSource);
        advisedSupport.setMethodInterceptor(methodInterceptor);
        advisedSupport.setMethodMatcher(methodMatcher);
    }
    
    @Test
    void testAdvisor() {
        WorldService worldService = new WorldServiceImpl();
        String expression = "execution(* org.springframework.test.service.WorldService.*(..))";
        AspectJExpressionPointcutAdvisor advisor = new AspectJExpressionPointcutAdvisor(expression);
        MethodBeforeAdviceInterceptor methodInterceptor = new MethodBeforeAdviceInterceptor(new WorldServiceBeforeAdvice());
        advisor.setAdvice(methodInterceptor);

        ClassFilter classFilter = advisor.getPointcut().getClassFilter();
        if (classFilter.matches(worldService.getClass())) {
            AdvisedSupport advisedSupport = new AdvisedSupport();
            TargetSource targetSource = new TargetSource(worldService);
            advisedSupport.setTargetSource(targetSource);
            advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
            advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
            
            WorldService proxy = (WorldService) new ProxyFactory(advisedSupport).getProxy();
            proxy.explode();
        }
    }
    
    @Test
    void testBeforeAdvice() {
        WorldServiceBeforeAdvice worldServiceBeforeAdvice = new WorldServiceBeforeAdvice();
        GenericInterceptor genericInterceptor = new GenericInterceptor();
        genericInterceptor.setBeforeAdvice(worldServiceBeforeAdvice);
        genericInterceptor.setAfterAdvice(new WorldServiceAfterAdvice());
        genericInterceptor.setAfterReturningAdvice(new WorldServiceAfterReturningAdvice());
        genericInterceptor.setThrowsAdvice(new WorldServiceThrowsAdvice());
        advisedSupport.setMethodInterceptor(genericInterceptor);
        
        WorldService proxy = (WorldService) new ProxyFactory(advisedSupport).getProxy();
        proxy.explode();
    }
    
    @Test
    void testJdkDynamicProxy() {
        WorldService proxy = (WorldService) new JdkDynamicAopProxy(advisedSupport).getProxy();
        proxy.explode();
    }
    
    @Test
    void testCglibDynamicProxy() {
        WorldService proxy = (WorldService) new CglibAopProxy(advisedSupport).getProxy();
        proxy.explode();
    }
    
    @Test
    void testProxyFactory() {
        advisedSupport.setProxyTargetClass(false);
        WorldService proxy = (WorldService) new ProxyFactory(advisedSupport).getProxy();
        System.out.println(proxy.getClass());
        proxy.explode();
        Assertions.assertTrue(proxy.getClass().getName().contains("Proxy"));
        
        advisedSupport.setProxyTargetClass(true);
        proxy = (WorldService) new ProxyFactory(advisedSupport).getProxy();
        System.out.println(proxy.getClass());
        Assertions.assertTrue(proxy.getClass().getName().contains("$$"));
        proxy.explode();
    }
}
