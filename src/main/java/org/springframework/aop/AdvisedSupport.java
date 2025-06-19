package org.springframework.aop;

import lombok.Data;
import org.aopalliance.intercept.MethodInterceptor;

@Data
public class AdvisedSupport {

    /**
     * 代理对象
     */
    private TargetSource targetSource;

    /**
     * 方法的拦截器
     */
    private MethodInterceptor methodInterceptor;

    /**
     * 方法匹配器
     */
    private MethodMatcher methodMatcher;
    
    // 是否使用 cglib 代理
    private boolean proxyTargetClass = false;
    
}
