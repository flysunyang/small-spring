package org.springframework.bean.factory.config;

import org.springframework.bean.BeansException;
import org.springframework.bean.factory.support.ConfigurableListableBeanFactory;

public interface BeanFactoryPostProcessor {

    void postProcessorBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;

}
