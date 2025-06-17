package org.springframework.beans.factory.support;

import lombok.Getter;
import org.springframework.beans.factory.config.BeanDefinitionRegistry;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {

    private ResourceLoader resourceLoader;

    @Getter
    private final BeanDefinitionRegistry beanDefinitionRegistry;

    public AbstractBeanDefinitionReader(ResourceLoader resourceLoader, BeanDefinitionRegistry beanDefinitionRegistry) {
        this.resourceLoader = resourceLoader;
        this.beanDefinitionRegistry = beanDefinitionRegistry;
    }

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry) {
        this.beanDefinitionRegistry = beanDefinitionRegistry;
        this.resourceLoader = new DefaultResourceLoader();
    }

    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }
}
