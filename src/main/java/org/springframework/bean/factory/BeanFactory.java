package org.springframework.bean.factory;

import org.springframework.bean.BeansException;

public interface BeanFactory {

    Object getBean(String beanName);

    <T> T getBean(String beanName, Class<T> type);

    <T> T getBean(Class<T> type) throws BeansException;
}
