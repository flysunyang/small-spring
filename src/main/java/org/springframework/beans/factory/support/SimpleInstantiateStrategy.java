package org.springframework.beans.factory.support;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;

public class SimpleInstantiateStrategy implements InstantiateStrategy {

    @Override
    public Object instantiate(BeanDefinition beanDefinition) throws BeansException {
        Class<?> beanClass = beanDefinition.getBeanClass();
        Object object;
        try {
            object = beanClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new BeansException("Failed to instantiate [" + beanClass.getName() + "]", e);
        }
        return object;
    }

}
