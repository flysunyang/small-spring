package org.springframework.test.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class UserServiceInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("Do something before the userServiceInterceptor " + invocation.getMethod().getName());
        Object proceed = invocation.proceed();
        System.out.println(proceed);
        System.out.println("Do something after the userServiceInterceptor " + invocation.getMethod().getName());
        return proceed;
    }

}
