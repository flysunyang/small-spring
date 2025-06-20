package org.springframework.beans.factory.config;

import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.util.StringValueResolver;

public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {
    
    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);
    
    String resolveEmbeddedValue(String value);

    void addEmbeddedValueResolver(StringValueResolver valueResolver);
}
