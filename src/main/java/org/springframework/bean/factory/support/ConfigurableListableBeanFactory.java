package org.springframework.bean.factory.support;

public interface ConfigurableListableBeanFactory extends ConfigurableBeanFactory, ListableBeanFactory {
    
    void preInstantiateSingletons();
    
}
