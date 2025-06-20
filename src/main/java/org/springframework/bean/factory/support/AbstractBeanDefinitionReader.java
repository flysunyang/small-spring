package org.springframework.bean.factory.support;

import org.springframework.bean.BeansException;
import org.springframework.core.DefaultResourceLoader;
import org.springframework.core.Resource;
import org.springframework.core.ResourceLoader;

public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {
    
    private ResourceLoader resourceLoader = new DefaultResourceLoader();

    @Override
    public void loadBeanDefinition(String location) throws BeansException {
        Resource resource = resourceLoader.getResource(location);
        loadBeanDefinition(resource);
    }

    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }

    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
}
