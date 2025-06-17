package org.springframework.beans.factory.support;

import org.springframework.beans.BeansException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

public interface BeanDefinitionReader {
    
    void loadBeanDefinitions(String location) throws BeansException;
    
    void loadBeanDefinitions(Resource resource) throws BeansException;
    
    void loadBeanDefinitions(String[] resource) throws BeansException;

    ResourceLoader getResourceLoader();
}
