package org.springframework.beans.factory.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ConfigurableListableBeanFactory;

public interface BeanFactoryPostProcessor {
    
    void postProcessorBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;
    
}
