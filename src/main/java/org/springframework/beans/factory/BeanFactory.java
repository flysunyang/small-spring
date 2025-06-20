package org.springframework.beans.factory;

import org.springframework.beans.BeansException;

public interface BeanFactory {

    Object getBean(String beanName) throws BeansException;
    
    Object getBean(Class<?> classType) throws BeansException;

    <T> T getBean(String beanName, Class<T> classType) throws BeansException;

}
