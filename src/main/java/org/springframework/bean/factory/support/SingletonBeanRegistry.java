package org.springframework.bean.factory.support;

public interface SingletonBeanRegistry {
    
    void addSingleton(String beanName, Object singleton);
    
    Object getSingleton(String beanName);

}
