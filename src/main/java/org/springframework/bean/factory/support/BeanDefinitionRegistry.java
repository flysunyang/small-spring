package org.springframework.bean.factory.support;

import org.springframework.bean.factory.BeanDefinition;

public interface BeanDefinitionRegistry {
    
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
    
}
