package org.springframework.beans.factory.support;

import org.springframework.beans.BeansException;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {
    
    private BeanDefinitionRegistry registry;
    
    private ResourceLoader resourceLoader;

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this(registry, new DefaultResourceLoader());
    }

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        this.registry = registry;
        this.resourceLoader = resourceLoader;
    }

    @Override
    public void loadBeanDefinitions(String location) throws BeansException {
        Resource resource = resourceLoader.getResource(location);
        loadBeanDefinitions(resource);
    }

    @Override
    public void loadBeanDefinitions(String[] locations) throws BeansException {
        for (String location : locations) {
            loadBeanDefinitions(location);
        }
    }

    @Override
    public BeanDefinitionRegistry getRegistry() {
        return registry;
    }

    @Override
    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }
}
