package org.springframework.bean.factory;

public interface BeanFactory {

    Object getBean(String beanName);

    <T> T getBean(String beanName, Class<T> type);
    
}
