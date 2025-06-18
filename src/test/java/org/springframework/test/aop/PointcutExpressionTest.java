package org.springframework.test.aop;

import cn.hutool.core.util.ClassUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.test.service.HelloService;

public class PointcutExpressionTest {

    @Test
    void testPointcutExpression() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut("execution(* org.springframework.test.service.HelloService.*(..))");
        Assertions.assertTrue(pointcut.matches(HelloService.class));
        Assertions.assertTrue(pointcut.matches(ClassUtil.getPublicMethod(HelloService.class, "sayHello"), HelloService.class));
    }
}
