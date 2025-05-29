package org.springframework.beans.factory.support;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;

public interface InstantiateStrategy {
    
    Object instantiate(BeanDefinition beanDefinition) throws BeansException;
    
}
