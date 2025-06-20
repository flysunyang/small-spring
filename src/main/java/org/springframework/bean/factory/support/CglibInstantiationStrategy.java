package org.springframework.bean.factory.support;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import org.springframework.bean.factory.BeanDefinition;

public class CglibInstantiationStrategy implements InstantiationStrategy {

    @Override
    public Object instantiate(String beanName, BeanDefinition beanDefinition) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(beanDefinition.getBeanClass());
        enhancer.setCallback((MethodInterceptor) (obj, method, args, proxy) -> proxy.invokeSuper(obj, args));
        return enhancer.create();
    }

}
