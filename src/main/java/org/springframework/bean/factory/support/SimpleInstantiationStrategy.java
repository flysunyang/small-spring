package org.springframework.bean.factory.support;

import org.springframework.bean.BeansException;
import org.springframework.bean.factory.BeanDefinition;

public class SimpleInstantiationStrategy implements InstantiationStrategy {

    @Override
    public Object instantiate(String beanName, BeanDefinition beanDefinition) {
        try {
            Class<?> beanClazz = beanDefinition.getBeanClass();
            return beanClazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new BeansException("Instantiate bean: '" + beanName + "' failed by JDK reflect method", e);
        }
    }

}
