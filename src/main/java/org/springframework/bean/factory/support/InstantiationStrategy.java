package org.springframework.bean.factory.support;

import org.springframework.bean.factory.BeanDefinition;

public interface InstantiationStrategy {
    
    Object instantiate(String beanName, BeanDefinition beanDefinition);
}
