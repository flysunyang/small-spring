package org.springframework.aop;

public interface Pointcut {
    
    ClassFilter classFilter();
    
    MethodMatcher methodMatcher();
    
}
