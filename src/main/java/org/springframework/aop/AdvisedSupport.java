package org.springframework.aop;

import lombok.Data;
import org.aopalliance.intercept.MethodInterceptor;

@Data
public class AdvisedSupport {
    
    private boolean proxyTargetClass = false;
    
    private TargetSource targetSource;
    
    private MethodMatcher methodMatcher;
    
    private MethodInterceptor methodInterceptor;
    
}
