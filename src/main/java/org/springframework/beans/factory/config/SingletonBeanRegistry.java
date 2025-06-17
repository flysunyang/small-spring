package org.springframework.beans.factory.config;

public interface SingletonBeanRegistry {

    void addSingleton(String beanName, Object singleton);

    Object getSingleton(String beanName);
}
