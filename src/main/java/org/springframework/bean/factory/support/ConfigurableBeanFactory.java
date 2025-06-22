package org.springframework.bean.factory.support;

import org.springframework.bean.BeansException;
import org.springframework.bean.factory.config.BeanPostProcessor;

import java.util.List;

public interface ConfigurableBeanFactory extends HierarchicalBeanFactory {

    Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException;

    Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException;

    List<BeanPostProcessor> getBeanPostProcessors();

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);
}
