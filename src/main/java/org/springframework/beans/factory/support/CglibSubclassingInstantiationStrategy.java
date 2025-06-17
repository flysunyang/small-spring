package org.springframework.beans.factory.support;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import org.springframework.beans.factory.config.BeanDefinition;

public class CglibSubclassingInstantiationStrategy implements InstantiationStrategy {
    
    @Override
    public Object instantiate(String beanName, BeanDefinition beanDefinition) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(beanDefinition.getBeanClass());
        enhancer.setCallback((MethodInterceptor) (obj, method, args, proxy) -> proxy.invokeSuper(obj, args));
        return enhancer.create();
    }
    
}
