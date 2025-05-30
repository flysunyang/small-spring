package org.springframework.beans.factory.config;

import org.springframework.beans.factory.BeanFactory;

public interface AutowireCapableBeanFactory extends BeanFactory {
    
    Object applyBeanPostProcessorBeforeInitialization(Object existingBean, String beanName);
    
    Object applyBeanPostProcessorAfterInitialization(Object existingBean, String beanName);
    
}
