package org.springframework.beans.factory.support;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;

public class SimpleInstantiationStrategy implements InstantiationStrategy {

    @Override
    public Object instantiate(String beanName, BeanDefinition beanDefinition) {
        try {
            return beanDefinition.getBeanClass().getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new BeansException("Failed to instantiate [" + beanName + "]", e);
        }
    }

}
