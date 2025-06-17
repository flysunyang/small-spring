package org.springframework.beans.factory.config;

import org.springframework.beans.BeansException;

public interface BeanDefinitionRegistry {
    
    void registry(String beanName, BeanDefinition beanDefinition);

    boolean containsBeanDefinition(String beanName);
    
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;
    
}
