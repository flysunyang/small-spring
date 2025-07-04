package org.springframework.beans.factory.config;

import org.springframework.beans.factory.HierarchicalBeanFactory;

public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {
    
    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);
    
}
