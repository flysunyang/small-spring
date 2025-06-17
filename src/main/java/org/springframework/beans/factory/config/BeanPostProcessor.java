package org.springframework.beans.factory.config;

import org.springframework.beans.BeansException;

public interface BeanPostProcessor {
    
    Object postProcessorBeforeInitialization(Object bean, String beanName) throws BeansException;
    
    Object postProcessorAfterInitialization(Object bean, String beanName) throws BeansException;
    
}
