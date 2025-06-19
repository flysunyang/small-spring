package org.springframework.aop;

import lombok.Setter;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class GenericInterceptor implements MethodInterceptor {
    
    @Setter
    private BeforeAdvice beforeAdvice;
    
    @Setter
    private AfterAdvice afterAdvice;

    @Setter
    private AfterReturningAdvice afterReturningAdvice;

    @Setter
    private ThrowsAdvice throwsAdvice;
    
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Object result = null;
        try {
            if (beforeAdvice != null) {
                beforeAdvice.before(invocation.getMethod(), invocation.getArguments(), invocation.getThis());
            }
            result = invocation.proceed();
        } catch (Exception ex) {
            if (throwsAdvice != null) {
                throwsAdvice.throwsHandle(ex, invocation.getMethod(), invocation.getArguments(), invocation.getThis());
            }
        } finally {
            if (afterAdvice != null) {
                afterAdvice.after(invocation.getMethod(), invocation.getArguments(), invocation.getThis());
            }
        }
        if (afterReturningAdvice != null) {
            afterReturningAdvice.afterReturning(result, invocation.getMethod(), invocation.getArguments(), invocation.getThis());
        }
        return result;
    }
    
}
