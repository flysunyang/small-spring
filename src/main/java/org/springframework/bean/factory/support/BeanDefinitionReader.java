package org.springframework.bean.factory.support;

import org.springframework.bean.BeansException;
import org.springframework.core.Resource;

public interface BeanDefinitionReader {
    
    BeanDefinitionRegistry getRegistry();
    
    void loadBeanDefinition(Resource resource) throws BeansException;
    
    void loadBeanDefinition(String location) throws BeansException;
    
    void loadBeanDefinitions(String[] locations) throws BeansException;
    
}
