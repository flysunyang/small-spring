package org.springframework.bean.factory.support;

import org.springframework.bean.factory.BeanDefinition;

public interface ConfigurableListableBeanFactory extends ConfigurableBeanFactory, ListableBeanFactory, SingletonBeanRegistry {
    
    void preInstantiateSingletons();

    BeanDefinition getBeanDefinition(String user);

    void destroySingletons();

}
