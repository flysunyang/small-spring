package org.springframework.aop.framework;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.springframework.aop.AdvisedSupport;

import java.lang.reflect.Method;

public class CglibAopProxy implements AopProxy {

    private final AdvisedSupport advised;

    public CglibAopProxy(AdvisedSupport advised) {
        this.advised = advised;
    }

    @Override
    public Object getProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(advised.getTargetSource().getTarget().getClass());
        enhancer.setInterfaces(advised.getTargetSource().getTargetClass());
        enhancer.setCallback(new DynamicAdvisedInterceptor(advised));
        return enhancer.create();
    }

    private static class DynamicAdvisedInterceptor implements MethodInterceptor {

        private final AdvisedSupport advised;

        public DynamicAdvisedInterceptor(AdvisedSupport advised) {
            this.advised = advised;
        }

        @Override
        public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
            if (advised.getMethodMatcher().matches(method)) {
                return advised.getMethodInterceptor().invoke(new CglibMethodInvocation(args, method, advised.getTargetSource().getTarget(), proxy));
            }
            return proxy.invokeSuper(obj, args);
        }
    }

    private static class CglibMethodInvocation extends ReflectiveMethodInvocation {

        private final MethodProxy methodProxy;

        public CglibMethodInvocation(Object[] arguments, Method method, Object target, MethodProxy methodProxy) {
            super(arguments, method, target);
            this.methodProxy = methodProxy;
        }

        @Override
        public Object proceed() throws Throwable {
            return methodProxy.invoke(this.target, arguments);
        }
    }
}
