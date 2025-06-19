package org.springframework.test.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.junit.jupiter.api.Test;
import org.springframework.aop.AdvisedSupport;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.TargetSource;
import org.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.framework.adapter.MethodBeforeAdviceInterceptor;
import org.springframework.test.advice.UserServiceBeforeAdvice;
import org.springframework.test.service.UserService;
import org.springframework.test.service.UserServiceImpl;

public class AdvisorTest {
    
    @Test
    void testAdvisor() {
        UserServiceImpl userService = new UserServiceImpl();
        AspectJExpressionPointcutAdvisor advisor = new AspectJExpressionPointcutAdvisor();
        advisor.setExpression("execution(* org.springframework.test.service.UserService.*(..))");
        advisor.setAdvice(new MethodBeforeAdviceInterceptor(new UserServiceBeforeAdvice()));

        ClassFilter classFilter = advisor.getPointcut().getClassFilter();
        if (classFilter.matches(userService.getClass())) {
            AdvisedSupport advised = new AdvisedSupport();
            advised.setTargetSource(new TargetSource(userService));
            advised.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
            advised.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
            UserService proxy = (UserService) new ProxyFactory(advised).getProxy();
            String res = proxy.register("bob");
            System.out.println(res);
        }
    }
}
